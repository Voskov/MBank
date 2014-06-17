package main.services;

import main.model.Client;

public interface Action {

    void updateClientDetails(Client client);

    void viewClientDetails();

    void viewAccountDetails();

    void viewClientDeposits();

    void viewClientActivities();

    void viewSystemProperty();
}
