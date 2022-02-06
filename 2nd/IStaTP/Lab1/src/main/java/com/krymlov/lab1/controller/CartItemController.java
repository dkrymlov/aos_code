package com.krymlov.lab1.controller;

import com.krymlov.lab1.entity.CartItemEntity;
import com.krymlov.lab1.repository.CartItemRepo;
import com.krymlov.lab1.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartItemController {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    @RequestMapping("/cart/add")
    public String getAddToCart(@RequestParam Long id, HttpServletRequest request){

        CartItemEntity cartItem = new CartItemEntity(itemRepo.findById(id).get());
        cartItemRepo.save(cartItem);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @RequestMapping("/cart/delete")
    public String getDeleteFromCart(@RequestParam Long id){

        cartItemRepo.deleteById(id);

        return "redirect:/cart";
    }

    @RequestMapping("/cart")
    public String getCartItems(Model model){

        int totalPrice = 0;
        List<String> items = new ArrayList<>();
        Iterable<CartItemEntity> cartItems = cartItemRepo.findAll();

        for (CartItemEntity cartItem : cartItems){
            totalPrice += cartItem.getItem().getPrice();
            items.add(cartItem.getItem().getId() + " " +
                    cartItem.getItem().getName() + " " +
                    cartItem.getItem().getBrand().getName() + " " +
                    cartItem.getItem().getSeller().getName());
        }

        model.addAttribute("items", items);
        model.addAttribute("totalPrice", totalPrice + " грн.");
        model.addAttribute("cartItems", cartItems);

        return "/cart/cart";
    }

    @RequestMapping("/cart/clean")
    public String getCleanCart(){

        cartItemRepo.deleteAll();

        return "redirect:/cart";
    }
}
