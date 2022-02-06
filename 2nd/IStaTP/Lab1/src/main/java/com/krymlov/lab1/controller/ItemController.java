package com.krymlov.lab1.controller;

import com.krymlov.lab1.entity.SellerEntity;
import com.krymlov.lab1.model.Item;
import com.krymlov.lab1.entity.BrandEntity;
import com.krymlov.lab1.entity.ItemEntity;
import com.krymlov.lab1.entity.CategoryEntity;
import com.krymlov.lab1.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class ItemController {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    private Map<String, Integer> brandsMap = new TreeMap<>();
    private Map<String, Integer> categoriesMap = new TreeMap<>();
    private Map<String, Integer> sellersMap = new TreeMap<>();

    private void initMaps(Iterable<BrandEntity> brands, Iterable<CategoryEntity> categories, Iterable<SellerEntity> sellers ){

        for (BrandEntity brand : brands){
            brandsMap.put(brand.getName(), itemRepo.countAllByBrandId(brand.getId()));
        }

        for (CategoryEntity category : categories){
            categoriesMap.put(category.getName(), itemRepo.countAllByCategoryId(category.getId()));
        }

        for (SellerEntity seller : sellers){
            sellersMap.put(seller.getName(), itemRepo.countAllBySellerId(seller.getId()));
        }
    }


    @RequestMapping("/item/create")
    public String getCreateItem(Model model){
        return "/items/create-item";
    }

    @PostMapping("/item/create")
    public String createItem(@Valid Item item, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes){

        String referer = request.getHeader("Referer");

        if (item.getBrand() != null && item.getCategory() != null && item.getSeller() != null){

            if (itemRepo.findByNameAndInfoAndCategoryIdAndBrandIdAndSellerId(item.getName(),item.getInfo(), item.getCategory().getId(),item.getBrand().getId(),item.getSeller().getId()) != null){
                ItemEntity itemEntity = itemRepo.findByNameAndInfoAndCategoryIdAndBrandIdAndSellerId
                        (item.getName(),item.getInfo(), item.getCategory().getId(),item.getBrand().getId(), item.getSeller().getId());
                if (itemEntity.getSeller().getId().equals(item.getSeller().getId())){
                    redirectAttributes.addFlashAttribute("wrongData",
                            "У однакових товарів повинен бути хоча б інший продавець.");
                    return "redirect:"+ referer;
                }
            }

            CategoryEntity categoryEntity = categoryRepo.findById(item.getCategory().getId()).get();
            BrandEntity brandEntity = brandRepo.findById(item.getBrand().getId()).get();
            SellerEntity sellerEntity = sellerRepo.findById(item.getSeller().getId()).get();

            ItemEntity itemEntity =
                    new ItemEntity(item.getName(), item.getInfo(), categoryEntity, brandEntity, sellerEntity, item.getPrice());

            itemRepo.save(itemEntity);

            return "redirect:/item";
        }

        redirectAttributes.addFlashAttribute("wrongData",
                "Ви ввели неправильний ідентифікатор для категорії/бреду/продавця");

        return "redirect:"+ referer;

    }

    @RequestMapping("/item")
    public String getItems(Model model){

        Iterable<BrandEntity> brands = brandRepo.findAll();
        Iterable<CategoryEntity> categories = categoryRepo.findAll();
        Iterable<SellerEntity> sellers = sellerRepo.findAll();
        Iterable<ItemEntity> items = itemRepo.findAll();

        initMaps(brands, categories, sellers);

        model.addAttribute("brandsData", brandsMap);
        model.addAttribute("categoriesData", categoriesMap);
        model.addAttribute("sellersData", sellersMap);
        model.addAttribute("items", items);

        return "items/item";
    }

    @RequestMapping("/item/sort/up")
    public String getSortUp(Model model){

        Iterable<BrandEntity> brands = brandRepo.findAll();
        Iterable<CategoryEntity> categories = categoryRepo.findAll();
        Iterable<SellerEntity> sellers = sellerRepo.findAll();
        Iterable<ItemEntity> items = itemRepo.findByOrderByPriceAsc();

        initMaps(brands, categories, sellers);

        model.addAttribute("brandsData", brandsMap);
        model.addAttribute("categoriesData", categoriesMap);
        model.addAttribute("sellersData", sellersMap);
        model.addAttribute("items", items);

        return "items/item";
    }

    @RequestMapping("/item/sort/down")
    public String getSortDown(Model model){

        Iterable<BrandEntity> brands = brandRepo.findAll();
        Iterable<CategoryEntity> categories = categoryRepo.findAll();
        Iterable<SellerEntity> sellers = sellerRepo.findAll();
        Iterable<ItemEntity> items = itemRepo.findByOrderByPriceDesc();

        initMaps(brands, categories, sellers);

        model.addAttribute("brandsData", brandsMap);
        model.addAttribute("categoriesData", categoriesMap);
        model.addAttribute("sellersData", sellersMap);
        model.addAttribute("items", items);

        return "items/item";
    }

    @PostMapping("/item/edit")
    public String editItem(@Valid Item item, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request){

        String referer = request.getHeader("Referer");

        if (item.getBrand() != null && item.getCategory() != null && item.getSeller() != null){

            if (itemRepo.findByNameAndInfoAndCategoryIdAndBrandIdAndSellerId(item.getName(),item.getInfo(), item.getCategory().getId(),item.getBrand().getId(),item.getSeller().getId()) != null){
                ItemEntity itemEntity = itemRepo.findByNameAndInfoAndCategoryIdAndBrandIdAndSellerId
                        (item.getName(),item.getInfo(), item.getCategory().getId(),item.getBrand().getId(), item.getSeller().getId());
                if (itemEntity.getSeller().getId().equals(item.getSeller().getId())){
                    redirectAttributes.addFlashAttribute("wrongData",
                            "У однакових товарів повинен бути хоча б інший продавець.");
                    return "redirect:"+ referer;
                }
            }

            ItemEntity itemEntity = itemRepo.findById(item.getId()).get();
            ItemEntity returnEntity =
                    new ItemEntity(item.getName(), item.getInfo(), item.getCategory(), item.getBrand(), item.getSeller(), item.getPrice());

            BeanUtils.copyProperties(returnEntity, itemEntity, "id");

            itemRepo.save(itemEntity);

            return "redirect:/item";
        }
        redirectAttributes.addFlashAttribute("wrongData",
                "Ви ввели неправильний ідентифікатор для категорії/бреду/продавця");

        return "redirect:"+ referer;
    }

    @RequestMapping("/item/edit")
    public String getEditItem(@Valid Item item, Model model){

        model.addAttribute("id", item.getId());
        model.addAttribute("name", item.getName());
        model.addAttribute("info", item.getInfo());
        model.addAttribute("category", item.getCategory().getId());
        model.addAttribute("brand", item.getBrand().getId());
        model.addAttribute("seller", item.getSeller().getId());
        model.addAttribute("price", item.getPrice());

        return "/items/edit-item";
    }

    @Transactional
    @PostMapping("/item/delete")
    public String deleteItem(@Valid Item item, RedirectAttributes redirectAttributes){

        ItemEntity itemEntity = itemRepo.findById(item.getId()).get();
        redirectAttributes.addFlashAttribute("deleted", "Товар з назвою " + itemEntity.getName() + " було видалено.");

        cartItemRepo.deleteAllByItem_Id(item.getId());

        itemRepo.deleteById(item.getId());

        return "redirect:/item";
    }

    @RequestMapping("/item/delete")
    public String getDeleteItem(@Valid Item item, Model model){

        model.addAttribute("id", item.getId());
        model.addAttribute("name", item.getName());
        model.addAttribute("info", item.getInfo());
        model.addAttribute("category", item.getCategory().getName());
        model.addAttribute("brand", item.getBrand().getName());
        model.addAttribute("seller", item.getSeller().getName());
        model.addAttribute("price", item.getPrice());

        return "/items/delete-item";
    }


}
