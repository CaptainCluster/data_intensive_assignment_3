package org.example.repository;

import jakarta.annotation.Resource;
import org.example.client.ClientConnection;
import org.example.dto.ProductDTO;
import org.example.utils.DatabaseUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static assignment.Tables.PRODUCT;

@Repository
public class ProductRepository {

    @Resource private ClientConnection clientConnection;
    @Resource private DatabaseUtils databaseUtils;

    public List<ProductDTO> getProducts() {
        if (!databaseUtils.isValidConnection()) {
            return List.of();
        }
        return clientConnection
                .getDslContext()
                .selectFrom(PRODUCT)
                .fetchInto(ProductDTO.class);
    }

    public ProductDTO getProductById(int productId) {
        if (!databaseUtils.isValidConnection()) {
            return null;
        }
        return clientConnection
                .getDslContext()
                .selectFrom(PRODUCT)
                .where(PRODUCT.ID.eq(productId))
                .fetchOneInto(ProductDTO.class);
    }

    public void updateQuantity(int productId, int quantity) {
        if (!databaseUtils.isValidConnection()) {
            return;
        }
         clientConnection
                .getDslContext()
                .update(PRODUCT)
                .set(PRODUCT.QUANTITY, quantity)
                .where(PRODUCT.ID.eq(productId))
                .execute();
    }

    public void updatePrice(int productId, int price) {
        if (!databaseUtils.isValidConnection()) {
            return;
        }
        clientConnection
                .getDslContext()
                .update(PRODUCT)
                .set(PRODUCT.PRICE, price)
                .where(PRODUCT.ID.eq(productId))
                .execute();
    }
}
