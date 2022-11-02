package com.increff.Assure.controller;

import com.increff.Assure.dto.UserDto;
import com.increff.exception.ApiException;
import com.increff.Assure.model.data.ProductData;
import com.increff.Assure.model.form.ProductForm;
import com.increff.Assure.model.form.UserForm;
import com.increff.Assure.util.UserType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static com.increff.Assure.util.RandomUtil.getRandomString;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = QAConfig.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration("src/test/webapp")
@Transactional
public class ProductControllerTest {

    @Resource
    private ProductController productController;

    @Resource
    private UserDto userDto;

    @Test
    public void bulkAddTest()throws ApiException
    {

        Long clientId = generateClientId();
        List<ProductForm> productFormList = new ArrayList<>();
        for(int i=0;i<5;i++)
        {
            productFormList.add(generateProductForm());
        }
        productController.bulkAdd(productFormList,clientId);

        Assert.assertEquals(5,productController.getAll().size());

    }

    @Test
    public void selectByIdTest()throws ApiException
    {
        List<ProductForm> productFormList = new ArrayList<>();
        productFormList.add(generateProductForm());
        productController.bulkAdd(productFormList,generateClientId());

        ProductData productData = productController.getAll().get(0);
        Long globalSkuId = productData.getGlobalSkuId();

        Assert.assertEquals(productController.getProductById(globalSkuId).getClientSkuId(),productData.getClientSkuId());

    }


    private ProductForm generateProductForm()throws ApiException
    {
        ProductForm productForm = new ProductForm();

        String clientSkuId = getRandomString();
        String name = getRandomString();
        String brandId = getRandomString();
        Double mrp = Math.random()*100%(20);
        String description = getRandomString();

        productForm.setClientSkuId(clientSkuId);
        productForm.setName(name);
        productForm.setBrandId(brandId);
        productForm.setMrp(mrp);
        productForm.setDescription(description);

        return productForm;
    }

    private Long generateClientId() throws ApiException {
        String name = getRandomString();
        UserType userType = UserType.CLIENT;

        UserForm userForm = new UserForm();
        userForm.setName(name);
        userForm.setUserType(userType);
        userDto.add(userForm);
        Long id = (userDto.getAll()).get(0).getId();
        return id;
    }
}
