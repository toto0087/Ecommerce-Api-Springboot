package com.api.eccom.repository;

import com.api.eccom.model.Invoice_Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface Invoice_DetailsRepository extends JpaRepository<Invoice_Details, Long> {

}
