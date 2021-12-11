package com.example.org.dao;

import com.example.org.bean.Organisation;

import java.util.List;

public interface OrganisationImpl {
    public boolean addOrganisation(Organisation organisation);
    public List<Organisation> getOrganisations();
    public Organisation getOrganisationById(int id);
}
