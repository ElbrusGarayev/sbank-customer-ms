package sbankcustomerms.mapper;

import org.mapstruct.Mapper;
import sbankcustomerms.dao.entity.Customer;
import sbankcustomerms.model.response.CustomerResponse;

@Mapper
public interface CustomerMapper {

    CustomerResponse toCustomerResponse(Customer customer);

}
