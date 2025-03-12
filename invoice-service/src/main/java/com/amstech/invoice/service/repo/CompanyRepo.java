package com.amstech.invoice.service.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.amstech.invoice.service.entity.Company;


@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {
	
		

	    @Query("SELECT c FROM Company c WHERE c.email = :email")
	    Company findByEmail(@Param("email") String email);

	    @Query("SELECT c FROM Company c WHERE c.companyPhone = :companyPhone")
	    Company findByCompanyPhone(@Param("companyPhone") String companyPhone);
	

	    @Query("SELECT c FROM Company c WHERE c.id = :id")
	    Company findByCompanyId(@Param("id") int id);
	    
	    @Query("SELECT c FROM Company c WHERE c.name = :name")
	    Company findByCompanyName(@Param("name") String name);  
	
//	    @Query("SELECT c FROM Company c WHERE c.email = :adminUsername or e.companyPhone = :adminUsername AND c.password = :password")
//	    Company findByAdminUserNameAndPassword(@Param("adminUsername") String adminUsername, @Param("password") String password);
//
//	
	    
	    @Query("SELECT c FROM Company c WHERE c.email = :adminUsername OR c.companyPhone = :adminUsername AND c.password = :password")
	    Company findByAdminUserNameAndPassword(@Param("adminUsername") String adminUsername, @Param("password") String password);

	    @Query("Update Company u set u.name=:name,u.address=:address,u.businessTypes=:businessTypes,u.companyPhone=:companyPhone,u.email=:email,u.logo=:logo,u.password=:password,u.taxPayer=:taxPayer,u.website=:website WHERE u.id=:id")
	    Company findById(@Param("id") int id,@Param("address") String address,@Param("businessTypes") int businessTypes,@Param("companyPhone") String companyPhone,@Param("email") String email,@Param("logo") String logo,@Param("password") String password,@Param("taxPayer") String taxPayer,@Param("website") String website);
	
	    

	    @Modifying
	    @Query("UPDATE Company c SET c.isDeleted = 1 WHERE c.id = :id")
	    void softDeleteCompany(@Param("id") int id);

	   
		 
		 @Query("SELECT c FROM Company c WHERE c.isDeleted = 0") 
		 List<Company> findAllCompany(Pageable pageable);
		    
		 
		 @Query("SELECT COUNT(c) FROM Company c WHERE c.isDeleted = 0")
		 long countAllCompany();

		 @Modifying
		 @Query("UPDATE Company c SET c.isDeleted = 0 WHERE c.id = :id")
		 void restoreCompany(@Param("id") Integer id);

	 

	}


