package com.krymlov.lab1.controller;

import com.krymlov.lab1.entity.BrandEntity;
import com.krymlov.lab1.entity.SellerEntity;
import com.krymlov.lab1.model.Category;
import com.krymlov.lab1.entity.ItemEntity;
import com.krymlov.lab1.entity.CategoryEntity;
import com.krymlov.lab1.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class CategoryController {

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    private Map<String, Integer> brandsMap = new TreeMap<>();
    private Map<String, Integer> sellersMap = new TreeMap<>();

    private void initMaps(Iterable<BrandEntity> brands, Iterable<SellerEntity> sellers, Category category){

        for (BrandEntity brand : brands){
            brandsMap.put(brand.getName(), itemRepo.countAllByBrandIdAndCategoryId(brand.getId(), category.getId()));
        }

        for (SellerEntity seller : sellers){
            sellersMap.put(seller.getName(), itemRepo.countAllBySellerIdAndCategoryId(seller.getId(), category.getId()));
        }

    }

    @RequestMapping("/category")
    public String getCategories(Model model){

        Iterable<CategoryEntity> categories = categoryRepo.findAll();

        model.addAttribute("categories", categories);

        return "categories/category";
    }

    @RequestMapping("/category/create")
    public String getCreateCategory(Model model){
        return "/categories/create-category";
    }

    @PostMapping("/category/create")
    public String createCategory(@Valid Category category, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes){

        String referer = request.getHeader("Referer");

        if (categoryRepo.findByName(category.getName()) != null){
            redirectAttributes.addFlashAttribute("wrongData", "Неможливо створити однакову категорію.");
            return "redirect:"+ referer;
        }

        CategoryEntity categoryEntity = new CategoryEntity(category.getName(), category.getInfo());
        categoryRepo.save(categoryEntity);

        return "redirect:/category";
    }

    @RequestMapping("/category/edit")
    public String getEditCategory(@Valid Category category, Model model) {

        model.addAttribute("id", category.getId());
        model.addAttribute("name", category.getName());
        model.addAttribute("info", category.getInfo());

        return "/categories/edit-category";
    }

    @PostMapping("/category/edit")
    public String editCategory(@Valid Category category, HttpServletRequest request, RedirectAttributes redirectAttributes){

        String referer = request.getHeader("Referer");

        if (categoryRepo.findByName(category.getName()) != null){
            redirectAttributes.addFlashAttribute("wrongData", "Неможливо створити однакову категорію.");
            return "redirect:"+ referer;
        }

        CategoryEntity categoryEntity = categoryRepo.findById(category.getId()).get();
        CategoryEntity returnEntity = new CategoryEntity(category.getName(), category.getInfo());

        BeanUtils.copyProperties(returnEntity, categoryEntity, "id");

        categoryRepo.save(categoryEntity);

        return "redirect:/category";
    }

    @RequestMapping("/category/delete")
    public String getDeleteCategory(@Valid Category category, Model model) {

        model.addAttribute("id", category.getId());
        model.addAttribute("name", category.getName());
        model.addAttribute("info", category.getInfo());

        return "/categories/delete-category";
    }

    @Transactional
    @PostMapping("/category/delete")
    public String deleteCategory(@Valid Category category, RedirectAttributes redirectAttributes){

        CategoryEntity categoryEntity = categoryRepo.findById(category.getId()).get();
        redirectAttributes.addFlashAttribute("deleted", "Категорію з назвою " + categoryEntity.getName() + " було видалено.");

        Iterable<ItemEntity> items = itemRepo.findAllByCategoryId(category.getId());
        for(ItemEntity item : items){
            cartItemRepo.deleteByItemId(item.getId());
        }

        itemRepo.deleteAllByCategory_Id(category.getId());
        categoryRepo.deleteById(category.getId());

        return "redirect:/category";
    }

    @RequestMapping("/category/details")
    public String getDetailsCategory(@Valid Category category, Model model){

        Iterable<BrandEntity> brands = brandRepo.findAll();
        Iterable<SellerEntity> sellers = sellerRepo.findAll();
        Iterable<ItemEntity> items = itemRepo.findAllByCategoryId(category.getId());

        initMaps(brands, sellers, category);

        model.addAttribute("brandsData", brandsMap);
        model.addAttribute("sellersData", sellersMap);

        model.addAttribute("name", category.getName());
        model.addAttribute("info", category.getInfo());
        model.addAttribute("items", items);

        return "/categories/details-category";
    }



}
