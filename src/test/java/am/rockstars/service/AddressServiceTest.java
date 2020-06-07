package am.rockstars.service;

import am.rockstars.dto.AddressPayload;
import am.rockstars.entity.Address;
import am.rockstars.entity.User;
import am.rockstars.repository.AddressRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Execution(ExecutionMode.CONCURRENT)
public class AddressServiceTest extends AbstractServiceUnitTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private UserService userService;

    @DisplayName("Should create address for provided payload")
    @Test
    void createAddress() {
        //Test data
        final User testUser = createTestUser();
        final EasyRandom easyRandom = new EasyRandom();
        final AddressPayload addressPayload = easyRandom.nextObject(AddressPayload.class);
        //Mock
        when(userService.getById(addressPayload.getUserId())).thenReturn(testUser);
        when(addressRepository.save(any(Address.class))).then(invocationOnMock -> invocationOnMock.getArgument(0));
        //Service call
        addressService.createAddress(addressPayload);
        //Verify
        verify(userService).getById(eq(addressPayload.getUserId()));
        final ArgumentCaptor<Address> addressArgumentCaptor = ArgumentCaptor.forClass(Address.class);
        verify(addressRepository).save(addressArgumentCaptor.capture());
        verifyNoMoreInteractions(userService, addressRepository);
        //Asserts
        final Address value = addressArgumentCaptor.getValue();
        assertThat(value).isEqualToIgnoringGivenFields(addressPayload, "user", "createdBy", "id", "createdAt", "updatedAt");
        assertThat(value.getUser()).isEqualTo(testUser);
    }

    @DisplayName("Should throw exception when address payload is null")
    @Test
    void createAddressWithInvalidArgument() {
        assertThrows(IllegalArgumentException.class,
                () -> addressService.createAddress(null), "Expected to throw IllegalArgumentException");
    }

    @DisplayName("Should return address for provided id")
    @Test
    void findById() {
        //Mock
        when(addressRepository.findById(eq(1L))).thenReturn(Optional.of(new Address()));
        //Service call
        final Address result = addressService.findById(1L);
        //Verify
        verify(addressRepository).findById(eq(1L));
        verifyNoMoreInteractions(addressRepository);
        //Asserts
        assertThat(result).isNotNull();
    }

    @DisplayName("Should return address for provided email")
    @Test
    void findByEmail() {
        //Mock
        when(addressRepository.findByUserId(eq(1L))).thenReturn(List.of(new Address()));
        //Service call
        final List<Address> result = addressService.findByUserId(1L);
        //Verify
        verify(addressRepository).findByUserId(eq(1L));
        verifyNoMoreInteractions(addressRepository);
        //Asserts
        assertThat(result).isNotEmpty();
    }

}
