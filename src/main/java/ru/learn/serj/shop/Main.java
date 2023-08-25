package ru.learn.serj.shop;

import ru.serj.learn.core.api.CreateOrderRequest;
import ru.serj.learn.core.service.OrderService;
import ru.serj.learn.postgres.domain.repo.OrderRepositoryImpl;
import ru.serj.learn.postgres.domain.repo.ProductRepositoryImpl;
import ru.serj.learn.postgres.domain.repo.UserRepositoryImpl;
import ru.serj.learn.postgres.service.TransactionManagerImpl;
import ru.serj.learn.application.observer.ApplicationCreateOrderObserverImpl;
import ru.serj.learn.telemetry.service.TimeSpanManagerImpl;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        // -telemetry
        TimeSpanManagerImpl timeSpanManager = new TimeSpanManagerImpl();


        // -postgres
        TransactionManagerImpl transactionManager = new TransactionManagerImpl();
        OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();


        // -application
        ApplicationCreateOrderObserverImpl observer = new ApplicationCreateOrderObserverImpl(transactionManager, timeSpanManager);


        // -core
        CreateOrderRequest request = new CreateOrderRequest(UUID.randomUUID(), UUID.randomUUID());
        OrderService coreService = new OrderService(userRepository, productRepository, orderRepository, observer);
        coreService.create(request);

    }
}

