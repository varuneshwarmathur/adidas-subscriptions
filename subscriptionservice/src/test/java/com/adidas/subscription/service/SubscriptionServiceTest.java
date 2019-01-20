package com.adidas.subscription.service;


import com.adidas.subscription.model.SubscriptionServiceData;
import com.adidas.subscription.model.SubscriptionServiceResponse;
import com.adidas.subscription.repository.SubscriptionServiceDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DuplicateKeyException;
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
        when(subscriptionServiceData.getEmailId()).thenReturn("");
        when(subscriptionServiceDAO.save(subscriptionServiceData)).thenThrow(DuplicateKeyException.class);
        SubscriptionServiceResponse subscriptionServiceResponse = subscriptionProcessorService.createSubscription(subscriptionServiceData);
        }

}
