package com.supmti.covoiturage.service;

import com.supmti.covoiturage.model.Alert;
import com.supmti.covoiturage.model.Annonce;
import com.supmti.covoiturage.model.Internaute;
import com.supmti.covoiturage.repo.AlertRepo;
import com.supmti.covoiturage.repo.AnnonceRepo;
import com.supmti.covoiturage.exception.AnnonceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AlertService {

    private final AlertRepo alertrepo;

    @Autowired
    public AlertService(AlertRepo alertrepo){this.alertrepo = alertrepo;}


    public Alert addAlert(Alert alert){

        return alertrepo.save(alert);
    }
}
