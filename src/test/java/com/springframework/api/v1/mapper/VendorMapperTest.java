package com.springframework.api.v1.mapper;

import com.springframework.api.v1.model.VendorDTO;
import com.springframework.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {

    public static final String NAME = "Western Tasty Fruits Ltd.";
    public static final long ID = 1L;

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTO() throws Exception {

        //given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        vendor.setId(ID);

        //when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        //then
        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    public void vendorDtoToVendor() {

        //todo - code and add a pull request
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        //when
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);

        //then
        assertEquals(NAME, vendor.getName());
    }
}