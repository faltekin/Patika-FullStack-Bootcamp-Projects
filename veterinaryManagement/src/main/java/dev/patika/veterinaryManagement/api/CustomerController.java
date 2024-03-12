package dev.patika.veterinaryManagement.api;

import dev.patika.veterinaryManagement.business.abstracts.ICustomerService;
import dev.patika.veterinaryManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagement.core.result.Result;
import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.core.utilities.ResultHelper;
import dev.patika.veterinaryManagement.dto.request.customer.CustomerSaveRequest;
import dev.patika.veterinaryManagement.dto.request.customer.CustomerUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.CustomerResponse;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;


    public CustomerController(ICustomerService customerService, IModelMapperService modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get (@PathVariable("id") long id) {
        Customer customer = this.customerService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(customer,CustomerResponse.class));
    }


    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)   // TODO Değerlendirme formu 10
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest ){

        return ResultHelper.created(customerService.save(customerSaveRequest));
    }


    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest ){

        return ResultHelper.created(customerService.update(customerUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.customerService.delete(id);
        return ResultHelper.Ok();
    }

    @GetMapping("/name/name/{name}")
    @ResponseStatus(HttpStatus.OK)      // TODO Değerlendirme formu 11
    public ResultData<List<CustomerResponse>> getCustomersByName(@PathVariable("name") String name) {

        return customerService.getCustomerByName(name);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CustomerResponse>> findAll(){

        return this.customerService.findAll();

    }


}
