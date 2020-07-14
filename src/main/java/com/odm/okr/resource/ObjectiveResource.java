package com.odm.okr.resource;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


import com.nytimes.spg.okr.server.api.ObjectiveApi;
import com.nytimes.spg.okr.server.model.Kr;
import com.nytimes.spg.okr.server.model.Objective;
import com.odm.okr.repository.ObjectiveRepository;
import com.odm.okr.entity.ObjectiveEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ObjectiveResource implements ObjectiveApi {

  @Autowired ObjectiveRepository objectiveRepository;
  
  @Override
  public ResponseEntity<Void> addObjective(Objective o) {
    objectiveRepository.save(mapObjectiveToObjectiveEntity(o));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Void> deleteObjective(Long id, String arg1) {
    if (!objectiveRepository.existsById(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    objectiveRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Kr>> getKrsById(Long arg0) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Objective> getObjectiveById(Long id) {
    if (!objectiveRepository.existsById(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    Optional<ObjectiveEntity> r = objectiveRepository.findById(id);
    return new ResponseEntity<>(mapObjectiveEntityToObjective(r.get()), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> updateObjective(Objective o) {
    if (!objectiveRepository.existsById(o.getId())) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    ObjectiveEntity entity = mapObjectiveToObjectiveEntity(o);
    objectiveRepository.save(entity);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  private ObjectiveEntity mapObjectiveToObjectiveEntity(Objective o) {
    ObjectiveEntity rv = new ObjectiveEntity();
    rv.setDescription(o.getDescription());
    rv.setCreatedBy(o.getCreatedBy().longValue());
    rv.setUpdatedBy(o.getCreatedBy().longValue());
    rv.setCreated(Instant.now());
    rv.setUpdated(Instant.now());
    rv.setStatus(o.getStatus().toString());
    rv.setParentId(o.getParentId());
    rv.setOrganizationId(o.getOrganizationId());
    return rv;
  }

  private Objective mapObjectiveEntityToObjective(ObjectiveEntity e) {
    Objective rv = new Objective();
    rv.setId(e.getId());
    rv.setDescription(e.getDescription());
    rv.setParentId(e.getParentId());
    return rv;
  }
  
}