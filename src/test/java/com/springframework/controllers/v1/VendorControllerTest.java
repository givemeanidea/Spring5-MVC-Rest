package com.springframework.controllers.v1;

import com.springframework.api.v1.model.VendorDTO;
import com.springframework.controllers.RestResponseEntityExceptionHandler;
import com.springframework.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.springframework.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getAllVendors() throws Exception {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Western Tasty Fruits Ltd.");

        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setName("Home Fruits");

        List<VendorDTO> vendors = Arrays.asList(vendorDTO, vendorDTO1);

        when(vendorService.getAllVendors()).thenReturn(vendors);

        mockMvc.perform(get(vendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("%.vendors", hasSize(2)));
    }

    @Test
    public void getVendorById() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Western Tasty Fruits Ltd.");

        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO);

        mockMvc.perform(get(vendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("%.vendors", equalTo("Western Tasty Fruits Ltd.")));
    }

    @Test
    public void createNewVendor() throws Exception {
        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName("Western Tasty Fruits Ltd.");

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendor.getName());
        returnDTO.setVendorUrl(vendorController.BASE_URL + "/1");

        when(vendorService.createNewVendor(vendor)).thenReturn(returnDTO);

        //then
        mockMvc.perform(post(vendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Fred")))
                .andExpect(jsonPath("$.vendorUrl", equalTo("/api/v1/vendors/1")));
    }

    @Test
    public void updateVendor() throws Exception {

        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName("Western Tasty Fruits Ltd.");

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendor.getName());
        returnDTO.setVendorUrl(vendorController.BASE_URL + "/1");

        when(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put(vendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Western Tasty Fruits Ltd.")))
                .andExpect(jsonPath("$.vendorUrl", equalTo("/api/v1/vendors/1")));
    }

    @Test
    public void patchVendor() throws Exception {
        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName("Western Tasty Fruits Ltd.");

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendor.getName());
        returnDTO.setVendorUrl(vendorController.BASE_URL + "/1");

        when(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put(vendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Western Tasty Fruits Ltd.")))
                .andExpect(jsonPath("$.vendorUrl", equalTo("/api/v1/vendors/1")));
    }

    @Test
    public void deleteVendor() throws Exception {

        mockMvc.perform(delete(vendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService).deleteVendorById(anyLong());
    }
}