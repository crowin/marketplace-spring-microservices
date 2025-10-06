package org.github.crowin.marketservice.repository;

import org.github.crowin.marketservice.repository.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

}
