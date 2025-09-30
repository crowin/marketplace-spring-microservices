package org.github.crowin.marketservice.repository;

import org.github.crowin.marketservice.repository.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    int countByUserId(Long userId);

    Page<Order> findByUserId(Long userId, Pageable pageable);
}
