package com.krymlov.lab1.controller;

import com.krymlov.lab1.entity.BrandEntity;
import com.krymlov.lab1.entity.CategoryEntity;
import com.krymlov.lab1.entity.ItemEntity;
import com.krymlov.lab1.entity.SellerEntity;
import com.krymlov.lab1.model.Seller;
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
public class SellerController {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    private Map<String, Integer> brandsMap = new TreeMap<>();
    private Map<String, Integer> categoriesMap = new TreeMap<>();

    private void initMaps(Iterable<BrandEntity> brands, Iterable<CategoryEntity> categories, Seller seller){
        for (BrandEntity brand : brands){
            brandsMap.put(brand.getName(), itemRepo.countAllByBrandIdAndSellerId(brand.getId(), seller.getId()));
        }

        for (CategoryEntity category : categories){
            categoriesMap.put(category.getName(), itemRepo.countAllByCategoryIdAndSellerId(category.getId(), seller.getId()));
        }
    }

    @RequestMapping("/seller")
    public String getSellers(Model model){

        Iterable<SellerEntity> sellers = sellerRepo.findAll();

        model.addAttribute("sellers", sellers);

        return "/sellers/seller";
    }

    @RequestMapping("/seller/create")
    public String getCreateSeller(Model model){
        return "/sellers/create-seller";
    }

    @PostMapping("/seller/create")
    public String createSeller(@Valid Seller seller, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes){

        String referer = request.getHeader("Referer");

        if (sellerRepo.findByName(seller.getName()) != null){
            redirectAttributes.addFlashAttribute("wrongData", "Неможливо створити ще одного продавця з такою назвою.");
            return "redirect:" + referer;
        }

        if (seller.getAccreditation() < 100000 || seller.getAccreditation() > 999999){
            redirectAttributes.addFlashAttribute("wrongData", "Ви ввели неправильний номер акредитації.");
            return "redirect:" + referer;
        }

        if (sellerRepo.findByAccreditation(seller.getAccreditation()) != null){
            redirectAttributes.addFlashAttribute("wrongData", "У всіх повинен бути унікальний номер акредитації.");
            return "redirect:" + referer;
        }

        SellerEntity sellerEntity = new SellerEntity(seller.getName(),seller.getInfo(), seller.getAccreditation());
        sellerRepo.save(sellerEntity);

        return "redirect:/seller";
    }

    @PostMapping("/seller/edit")
    public String editSeller(@Valid Seller seller, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes){

        SellerEntity sellerEntity = sellerRepo.findById(seller.getId()).get();
        SellerEntity returnEntity = new SellerEntity(seller.getName(), seller.getInfo(), seller.getAccreditation());
        String referer = request.getHeader("Referer");

        SellerEntity temp = sellerRepo.findById(seller.getId()).get();
        if (temp.getName().equals(seller.getName())){

            if (seller.getAccreditation() < 100000 || seller.getAccreditation() > 999999){
                redirectAttributes.addFlashAttribute("wrongData", "Ви ввели неправильний номер акредитації.");
                return "redirect:" + referer;
            }

            if (!temp.getAccreditation().equals(seller.getAccreditation())){
                if (sellerRepo.findByAccreditation(seller.getAccreditation()) != null){
                    redirectAttributes.addFlashAttribute("wrongData", "У всіх повинен бути унікальний номер акредитації.");
                    return "redirect:" + referer;
                }
            }

            BeanUtils.copyProperties(returnEntity, sellerEntity, "id");

            sellerRepo.save(sellerEntity);

            return "redirect:/seller";
        }

        if (sellerRepo.findByName(seller.getName()) != null){
            redirectAttributes.addFlashAttribute("wrongData", "Неможливо створити ще одного продавця з такою назвою.");
            return "redirect:" + referer;
        }

        if (seller.getAccreditation() < 100000 || seller.getAccreditation() > 999999){
            redirectAttributes.addFlashAttribute("wrongData", "Ви ввели неправильний номер акредитації.");
            return "redirect:" + referer;
        }

        if (sellerRepo.findByAccreditation(seller.getAccreditation()) != null){
            redirectAttributes.addFlashAttribute("wrongData", "У всіх повинен бути унікальний номер акредитації.");
            return "redirect:" + referer;
        }

        BeanUtils.copyProperties(returnEntity, sellerEntity, "id");

        sellerRepo.save(sellerEntity);

        return "redirect:/seller";

    }

    @RequestMapping("/seller/edit")
    public String getEditSeller(@Valid Seller seller, Model model){

        model.addAttribute("id", seller.getId());
        model.addAttribute("name", seller.getName());
        model.addAttribute("info", seller.getInfo());
        model.addAttribute("accreditation", seller.getAccreditation());

        return "/sellers/edit-seller";
    }

    @Transactional
    @PostMapping("/seller/delete")
    public String deleteSeller(@Valid Seller seller, RedirectAttributes redirectAttributes){

        SellerEntity sellerEntity = sellerRepo.findById(seller.getId()).get();
        redirectAttributes.addFlashAttribute("deleted", "Продавця з назвою " + sellerEntity.getName() + " було видалено.");

        Iterable<ItemEntity> items = itemRepo.findAllBySellerId(seller.getId());
        for (ItemEntity item : items){
            cartItemRepo.deleteByItemId(item.getId());
        }

        itemRepo.deleteAllBySellerId(seller.getId());
        sellerRepo.deleteById(seller.getId());

        return "redirect:/seller";
    }

    @RequestMapping("/seller/delete")
    public String getDeleteSeller(@Valid Seller seller, Model model){

        model.addAttribute("id", seller.getId());
        model.addAttribute("name", seller.getName());
        model.addAttribute("info", seller.getInfo());
        model.addAttribute("accreditation", seller.getAccreditation());

        return "/sellers/delete-seller";
    }

    @RequestMapping("/seller/details")
    public String getDetailsSeller(@Valid Seller seller, Model model){

        Iterable<BrandEntity> brands = brandRepo.findAll();
        Iterable<CategoryEntity> categories = categoryRepo.findAll();
        Iterable<ItemEntity> items = itemRepo.findAllBySellerId(seller.getId());

        initMaps(brands, categories, seller);

        model.addAttribute("brandsData", brandsMap);
        model.addAttribute("categoriesData", categoriesMap);

        model.addAttribute("name", seller.getName());
        model.addAttribute("info", seller.getInfo());
        model.addAttribute("accreditation", seller.getAccreditation());
        model.addAttribute("items", items);

        return "/sellers/details-seller";
    }

}
