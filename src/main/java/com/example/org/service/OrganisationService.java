package com.example.org.service;

import com.example.org.bean.Organisation;
import com.example.org.dao.OrganisationDAO;

import java.util.List;

public class OrganisationService {
    OrganisationDAO dao = new OrganisationDAO();
    public boolean addOrganisation(Organisation org)
    {
        return dao.addOrganisation(org);
    }
    public Organisation getOrganisationById(int id)
    {
        return dao.getOrganisationById(id);
    }
    public List<Organisation> getOrganisations()
    {
        return dao.getOrganisations();
    }
}
