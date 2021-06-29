package com.supmti.covoiturage.repo;


import com.supmti.covoiturage.model.Alert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlertRepo extends JpaRepository<Alert, Long> {







}
