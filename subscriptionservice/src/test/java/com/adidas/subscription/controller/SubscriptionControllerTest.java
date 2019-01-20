/**
 *
 */
package com.adidas.subscription.controller;

import com.adidas.subscription.controller.SubscriptionServiceController;
import com.adidas.subscription.model.SubscriptionRequestModel;
import com.adidas.subscription.model.SubscriptionServiceData;
import com.adidas.subscription.model.SubscriptionServiceResponse;
import com.adidas.subscription.service.SubscriptionProcessorService;

import com.adidas.subscription.service.SubscriptionProcessorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author  varmathu0
 * @project Credit-Card-Processor
 */

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionControllerTest {

    @Mock
    SubscriptionProcessorService processorService;

    @InjectMocks
    SubscriptionServiceController subscriptionServiceController;

    @Test
    public void test_createValidSubscription() {
        SubscriptionRequestModel subscriptionRequestModel = new SubscriptionRequestModel();
        subscriptionRequestModel.setFirstName("Varuneshwar");
        subscriptionRequestModel.setEmailId("mathur_varunesh@yahoo.com");
        subscriptionRequestModel.setGender("male");
        subscriptionRequestModel.setDateOfBirth("19-08-1987");
        subscriptionRequestModel.setConsentGranted(Boolean.TRUE);

        SubscriptionServiceResponse response = mock(SubscriptionServiceResponse.class);
        when(processorService.createSubscription(Mockito.any())).thenReturn(response);
        SubscriptionServiceResponse subscriptionServiceResponse =
                subscriptionServiceController.createSubscription(subscriptionRequestModel);
        assertEquals(response,subscriptionServiceResponse);
    }
}
