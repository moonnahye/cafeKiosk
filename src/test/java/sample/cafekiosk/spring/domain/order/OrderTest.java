package sample.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;

class OrderTest {

    @DisplayName("주문 생성 시 상품 리스트에서 주뭉의 총 금액을 계산하다.")
    @Test
    void calculateTotalPrice(){
        // given
        List<Product> products = List.of (
            createProduct("001", 1000),
            createProduct("002", 2000)
        );

        // when
        Order order = Order.create(products, LocalDateTime.now());

        // then
        assertThat(order.getTotalPrice()).isEqualTo(3000);
    }

    @DisplayName("주문 생성 시, 주문 상태는 INIT이다.")
    @Test
    void init(){
        // given
        List<Product> products = List.of (
                createProduct("001", 1000),
                createProduct("002", 2000)
        );

        // when
        Order order = Order.create(products, LocalDateTime.now());

        // then
        assertThat(order.getOrderStatus()).isEqualByComparingTo(OrderStatus.INIT);
    }

    @DisplayName("주문 생성 시 등록 시간을 기록한다")
    @Test
    void registeredLocalTime(){
        // given
        LocalDateTime registeredTime = LocalDateTime.now();
        List<Product> products = List.of (
                createProduct("001", 1000),
                createProduct("002", 2000)
        );

        // when
        Order order = Order.create(products, registeredTime);

        // then
        assertThat(order.getRegisteredDateTime()).isEqualTo(registeredTime);
    }

    private Product createProduct(String productNumber, int price ){
        return Product.builder()
                .type(ProductType.HANDMADE)
                .productNumber(productNumber)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴이름")
                .build();
    }
}