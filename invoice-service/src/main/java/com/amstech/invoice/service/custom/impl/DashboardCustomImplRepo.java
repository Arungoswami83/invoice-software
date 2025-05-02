package com.amstech.invoice.service.custom.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.amstech.invoice.service.entity.Dashboard;
import com.amstech.invoice.service.repo.custom.DashboardCustomRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DashboardCustomImplRepo implements DashboardCustomRepo {
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Dashboard> searchDashboardData(String type, Integer page, Integer size, Integer clientId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal minSales, BigDecimal maxSales, String status, String gender) {
        
        String queryStr = createQuery("e", clientId, startDate, endDate, minSales, maxSales, status, gender);
        log.info("Executing Query: {}", queryStr);

        TypedQuery<Dashboard> query = entityManager.createQuery(queryStr, Dashboard.class);
        setQueryParameters(query, clientId, startDate, endDate, minSales, maxSales, status, gender);
        
        if (page != null && size != null) {
            query.setFirstResult(page * size);
            query.setMaxResults(size);
        }

        return query.getResultList();
    }

    @Override
    public long countBy(String type, Integer clientId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal minSales, BigDecimal maxSales, String status, String gender) {
        String queryStr = createQuery("COUNT(e)", clientId, startDate, endDate, minSales, maxSales, status, gender);
        log.info("Executing Count Query: {}", queryStr);

        TypedQuery<Long> query = entityManager.createQuery(queryStr, Long.class);
        setQueryParameters(query, clientId, startDate, endDate, minSales, maxSales, status, gender);

        return query.getSingleResult();
    }

    private String createQuery(String type, Integer clientId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal minSales, BigDecimal maxSales, String status, String gender) {
        StringBuilder query = new StringBuilder("SELECT " + type + " FROM Dashboard e ");
        boolean isWhereAdded = false;

        if (clientId != null) {
            if (!isWhereAdded) {
                query.append(" WHERE");
                isWhereAdded = true;
            } else {
                query.append(" AND");
            }
            query.append(" e.client.id = :clientId ");
        }
        if (gender != null) {
			if (!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			} else {
				query.append(" and ");
			}
			query.append(" e.gender = :gender ");
		}
        if (status != null) {
            if (!isWhereAdded) {
                query.append(" WHERE");
                isWhereAdded = true;
            } else {
                query.append(" AND");
            }
            query.append(" e.isDeleted = :status ");
        }
        if (startDate != null && endDate != null) {
        	if (!isWhereAdded) {
				query.append("where");
				isWhereAdded = true;
			} else {
				query.append("and");
			}
			query.append(" e.createdAt BETWEEN :startDate AND :endDate ");
		}
        if (minSales != null && maxSales != null) {
        	if (!isWhereAdded) {
				query.append("where");
				isWhereAdded = true;
			} else {
				query.append("and");
			}
			query.append(" e.sales BETWEEN :minSales AND :maxSales ");
		}
        return query.toString();
    }

    private void setQueryParameters(TypedQuery<?> query, Integer clientId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal minSales, BigDecimal maxSales, String status, String gender) {
        if (clientId != null) {
            query.setParameter("clientId", clientId);
        }
        if (gender != null) {
            query.setParameter("gender", gender);
        }
        if (status != null) {
            query.setParameter("status", status);
        }
        if (startDate != null && endDate != null) {
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
        }
        if (minSales != null && maxSales != null) {
            query.setParameter("minSales", minSales);
            query.setParameter("maxSales", maxSales);
        }
    }
}
