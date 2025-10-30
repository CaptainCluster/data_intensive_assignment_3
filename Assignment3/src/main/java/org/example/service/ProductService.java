package org.example.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProductDTO;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Slf4j
@Service
public class ProductService {
    @Resource private ProductRepository productRepository;

    private final Scanner scanner = new Scanner(System.in);

    public void listProducts() {
        productRepository.getProducts()
                .forEach(productDTO -> log.info(
                        "ID - {} | name - {} | price - {} | quantity - {}",
                        productDTO.getId(),
                        productDTO.getName(),
                        productDTO.getPrice() != null ? productDTO.getPrice() : "NO PRICE",
                        productDTO.getQuantity()
                ));
    }

    public void restockProduct() {
        log.info("What is the ID of the product for restocking: ");
        int productId;
        try {
            String idString = scanner.nextLine();
            productId = Integer.parseInt(idString);
        } catch (Exception e) {
            log.warn("Error when parsing the id. Cancelling the restocking process.");
            return;
        }

        log.info("How many products will be in the restock: ");
        int numberOfNewProducts;
        try {
            String numberOfNewProductsString = scanner.nextLine();
            numberOfNewProducts = Integer.parseInt(numberOfNewProductsString);
        } catch (Exception e) {
            log.warn("Error when parsing the number. Cancelling the restocking process.");
            return;
        }
        ProductDTO productDTO = productRepository.getProductById(productId);
        productRepository.updateQuantity(
                productDTO.getId(),
                productDTO.getQuantity() + numberOfNewProducts
        );
        log.info("{} has been restocked", productDTO.getName());
    }

    public void changeProductPrice() {
        log.info("What is the ID of the product for a price change: ");
        int productId;
        try {
            String idString = scanner.nextLine();
            productId = Integer.parseInt(idString);
        } catch (Exception e) {
            log.warn("Error when parsing the id. Cancelling the price changing process.");
            return;
        }
        log.info("What will be the new price: ");
        int price;
        try {
            String priceString = scanner.nextLine();
            price = Integer.parseInt(priceString);
        } catch (Exception e) {
            log.warn("Error when parsing the number. Cancelling the restocking process.");
            return;
        }

        ProductDTO productDTO = productRepository.getProductById(productId);
        productRepository.updatePrice(
                productDTO.getId(),
                price
        );
        log.info("{} has been given a new price", productDTO.getName());
    }
}
