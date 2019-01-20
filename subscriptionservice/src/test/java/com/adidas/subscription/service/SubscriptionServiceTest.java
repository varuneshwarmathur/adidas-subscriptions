package com.adidas.subscription.service;


import com.adidas.subscription.model.SubscriptionRequestModel;
import com.adidas.subscription.model.SubscriptionServiceData;
import com.adidas.subscription.model.SubscriptionServiceResponse;
import com.adidas.subscription.repository.SubscriptionServiceDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author  varmathu0
 */

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionServiceTest {

    @Mock
    private SubscriptionServiceDAO subscriptionServiceDAO;

    @InjectMocks
    private SubscriptionProcessorServiceImpl subscriptionProcessorService;

    @Test
    public void test_createSubscription() {
        SubscriptionServiceData subscriptionServiceData = mock(SubscriptionServiceData.class);
        when(subscriptionServiceData.getId()).thenReturn("12321321321312");
        SubscriptionServiceResponse response = mock(SubscriptionServiceResponse.class);
        when(subscriptionProcessorService.createSubscription(Mockito.any())).thenReturn(response);
        SubscriptionServiceResponse subscriptionServiceResponse =
                subscriptionProcessorService.createSubscription(subscriptionServiceData);
        assertEquals(response,subscriptionServiceResponse);
    }

}
