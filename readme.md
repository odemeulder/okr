# Getting Started

# generate server stubs

go to `~/dev/spg-specs` and run `./gradlew build`, the jar will be in this project. 
copy it to the current project. 
`cp ~/dev/spg-specs/build/okr/java/server/build/libs/okr-server-1.0.0.jar ~/dev/okr/libs`

# useful curls

```bash
curl -X POST -d '{"id": 1,"description": "win all the okrs","organization_id": 2,"parent_id": 0,"status": "Active","createdBy": 1}' -H "Content-Type: application/json" http://localhost:8080/objective

curl -X GET  http://localhost:8080/objective/1
```

# Data Model

## Entities

* Objective
  - id
  - description
  - status
  - planning_period_id
  - parent_id
  - organization_id
  - created_by
  - created
  - updated_by
  - updated
* Kr
  - id
  - description
  - baseline
  - major_initiative
  - result
  - status
  - objective_id
  - score
  - created_by
  - created
  - updated_by
  - updated
* User
  - id
  - first_name
  - last_name
  - email
* PlanningPeriod
  - id
  - description
  - status
* Comment
  - id
  - comment
  - parent_id
  - entity
  - created_by
  - created
  - updated_by
  - updated
* Organization
  - id
  - name
  - org_level
  - parent_id

## Enums

* ObjectiveStatus
  - Proposed
  - Active
  - InProgress
  - Achieved
  - PartiallyAchieved
  - Cancelled  
* KrStatus
  - Proposed
  - Active
  - InProgress
  - Done
  - Cancelled
* Entity
  - Objective
  - Kr
  - Comment
* OrgLevel
  - Company
  - Mission
  - Group
  - Team
* TimespanStatus
  - Past
  - Current
  - Future
