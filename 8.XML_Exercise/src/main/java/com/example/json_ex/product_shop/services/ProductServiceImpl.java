package com.example.json_ex.product_shop.services;

import com.example.json_ex.product_shop.entities.products.Product;
import com.example.json_ex.product_shop.entities.products.ProductOneDTO;
import com.example.json_ex.product_shop.entities.users.User;
import com.example.json_ex.product_shop.repositories.ProductRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
   /* private final ModelMapper mapper;
    private final TypeMap<Product, ProductOneDTO> productProductOneDTOTypeMap;*/

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;

       /* this.mapper=new ModelMapper();
        Converter<User, String> userToFullNameConverter=
                context -> context.getSource()==null ? null: context.getSource().getFullName();

        TypeMap<Product, ProductOneDTO> typeMap = this.mapper.createTypeMap(Product.class, ProductOneDTO.class);
      this.productProductOneDTOTypeMap = typeMap.addMappings(map ->
                map.using(userToFullNameConverter).map(Product::getSeller, ProductOneDTO::setSeller));


        this.mapper.addConverter(userToFullNameConverter);*/
    }

    @Override
    public List<ProductOneDTO> getProductWithPriceAndFullNameOfTheSeller(BigDecimal bigDecimal, BigDecimal bigDecimal1) {
        ////Ако от репозиторито връщах Product, а не директно обекта, който желая ще трябва да използвам конвертер-а
        ///на mapper за да преобразувам името във first+last и след това трябва да
        // mappna ot Prdouct.class към желания формат на класа и да го върна към следващия слой
        //products.stream().map(product-> this.productProductOneDTOTypeMap.map(product)).collect(toList()));

        return this.productRepository.getProductBy(bigDecimal,bigDecimal1);
    }
}
