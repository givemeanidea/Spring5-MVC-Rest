package com.springframework.services;

import com.springframework.api.v1.mapper.VendorMapper;
import com.springframework.api.v1.model.VendorDTO;
import com.springframework.domain.Vendor;
import com.springframework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

    public static final Long ID = 2L;
    public static final String NAME = "Western Tasty Fruits Ltd.";
    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void getAllVendors() throws Exception {

        //given
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        //when
        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        //then
        assertEquals(3, vendorDTOS.size());
    }

    @Test
    public void getVendorById() {

        //given
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        when(vendorRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(vendor));

        //when
        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        //then
        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    public void createNewVendor() {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Daniel");

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedDto = vendorService.createNewVendor(vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedDto.getName());
        assertEquals("/api/v1/vendors/1", savedDto.getVendorUrl());
    }

    @Test
    public void saveVendorByDTO() {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Daniel");

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedDto = vendorService.saveVendorByDTO(1L, vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedDto.getName());
        assertEquals("/api/v1/vendors/1", savedDto.getVendorUrl());
    }

    @Test
    public void patchVendor() {
        // todo
    }

    @Test
    public void deleteVendorById() {
        // todo
    }
}