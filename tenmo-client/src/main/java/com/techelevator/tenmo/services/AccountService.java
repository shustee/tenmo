package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.*;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/*  Service class used to send HTTP requests to server to access user balance, list users, create transfers, update
    transfers, and list transfers.
 */

public class AccountService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    private AuthenticatedUser user;

    public AccountService(String url) {
        this.baseUrl = url;
    }

    public void setUser(AuthenticatedUser user) {
        this.user = user;
    }
<<<<<<< HEAD

    // Retrieves balance for current user.
=======
    //returns balance of the current account
>>>>>>> 7f8fadbb673312e81fd0be8206ac0070f5a817de
    public BigDecimal getBalance() {
        BigDecimal balance = null;
        try {
            ResponseEntity<BigDecimal> response =
            restTemplate.exchange(baseUrl + "balance", HttpMethod.GET, makeAuthEntity(), BigDecimal.class);
            balance = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }
<<<<<<< HEAD

    // Retrieves list of users registered.
=======
    //Get all users accounts (do not show balance)
>>>>>>> 7f8fadbb673312e81fd0be8206ac0070f5a817de
    public User[] getUsers() {
        User[] users = null;
        try {
            ResponseEntity<User[]> response =
                    restTemplate.exchange(baseUrl + "user", HttpMethod.GET, makeAuthEntity(), User[].class);
            users = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return users;
    }
<<<<<<< HEAD

    // Retrieves a specific user by the user id
=======
    //Get a user by their ID
>>>>>>> 7f8fadbb673312e81fd0be8206ac0070f5a817de
    public User getUserById(Long id) {
        User user = null;
        try {
            ResponseEntity<User> response =
                    restTemplate.exchange(baseUrl + "user/" + id, HttpMethod.GET, makeAuthEntity(), User.class);
            user = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return user;
    }
<<<<<<< HEAD

    // Creates a new transfer based on the Transfer model class
=======
    //Create a transfer to be used to send funds
>>>>>>> 7f8fadbb673312e81fd0be8206ac0070f5a817de
    public Transfer createTransfer(Transfer transfer) {
        HttpEntity<Transfer> entity = makeTransferEntity(transfer);
        Transfer returnedTransfer = null;
        try {
            ResponseEntity<Transfer> response =
                    restTemplate.exchange(baseUrl + "transfer", HttpMethod.POST, entity, Transfer.class);
            returnedTransfer = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return returnedTransfer;
    }
<<<<<<< HEAD

    // Retrieves full list of transfers for user, sent and received
=======
    //Request the history of all transfers
>>>>>>> 7f8fadbb673312e81fd0be8206ac0070f5a817de
    public Transfer[] getTransferHistory() {
        Transfer[] transfers = null;
        try {
            ResponseEntity<Transfer[]> response =
                    restTemplate.exchange(baseUrl + "transfer/history", HttpMethod.GET,
                            makeAuthEntity(), Transfer[].class);
            transfers = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfers;
    }
<<<<<<< HEAD

    // Retrieves list of transfers for the user, sent and received, with the status of pending
=======
    //Show only transfers that = pending
>>>>>>> 7f8fadbb673312e81fd0be8206ac0070f5a817de
    public Transfer[] getPendingTransfers() {
        Transfer[] pendingTransfers = null;
        try {
            ResponseEntity<Transfer[]> response =
                    restTemplate.exchange(baseUrl + "transfer/pending", HttpMethod.GET,
                            makeAuthEntity(), Transfer[].class);
            pendingTransfers = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return pendingTransfers;
    }
<<<<<<< HEAD

    // Updates the status of an existing transfer
=======
    //Change transfer information.
>>>>>>> 7f8fadbb673312e81fd0be8206ac0070f5a817de
    public boolean updateTransfer(Transfer transfer) {
        HttpEntity<Transfer> entity = makeTransferEntity(transfer);
        boolean success = false;
        long transferId = transfer.getTransferId();
        try {
            restTemplate.put(baseUrl + "transfer/" + transferId, entity);
            success = true;
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return success;
    }
    //Auth items that will allow users to access application
    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(user.getToken());
        return new HttpEntity<>(transfer, headers);
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(user.getToken());
        return new HttpEntity<>(headers);
    }


}
