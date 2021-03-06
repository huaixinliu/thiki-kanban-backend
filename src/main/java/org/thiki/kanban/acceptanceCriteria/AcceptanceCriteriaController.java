package org.thiki.kanban.acceptanceCriteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.thiki.kanban.foundation.common.Response;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xubt on 10/17/16.
 */
@RestController
public class AcceptanceCriteriaController {
    @Autowired
    private AcceptanceCriteriaService acceptanceCriteriaService;
    @Resource
    private AcceptanceCriteriasResource acceptanceCriteriasResource;
    @Resource
    private AcceptanceCriteriaResource acceptanceCriteriaResource;

    @RequestMapping(value = "/boards/{boardId}/stages/{stageId}/cards/{cardId}/acceptanceCriterias", method = RequestMethod.POST)
    public HttpEntity create(@RequestBody AcceptanceCriteria acceptanceCriteria, @RequestHeader String userName, @PathVariable String boardId, @PathVariable String stageId, @PathVariable String cardId) throws Exception {
        AcceptanceCriteria savedAcceptanceCriteria = acceptanceCriteriaService.addAcceptCriteria(userName, cardId, acceptanceCriteria);
        return Response.post(acceptanceCriteriaResource.toResource(savedAcceptanceCriteria, boardId, stageId, cardId, userName));
    }

    @RequestMapping(value = "/boards/{boardId}/stages/{stageId}/cards/{cardId}/acceptanceCriterias/{acceptanceCriteriaId}", method = RequestMethod.PUT)
    public HttpEntity updateAcceptCriteria(@RequestBody AcceptanceCriteria acceptanceCriteria, @PathVariable String boardId, @PathVariable String stageId, @PathVariable String cardId, @PathVariable String acceptanceCriteriaId, @RequestHeader String userName) throws Exception {
        AcceptanceCriteria savedAcceptanceCriteria = acceptanceCriteriaService.updateAcceptCriteria(cardId, acceptanceCriteriaId, acceptanceCriteria, userName);
        return Response.build(acceptanceCriteriaResource.toResource(savedAcceptanceCriteria, boardId, stageId, cardId, userName));
    }

    @RequestMapping(value = "/boards/{boardId}/stages/{stageId}/cards/{cardId}/acceptanceCriterias/{acceptanceCriteriaId}", method = RequestMethod.GET)
    public HttpEntity findById(@PathVariable String boardId, @PathVariable String stageId, @PathVariable String cardId, @PathVariable String acceptanceCriteriaId, @RequestHeader String userName) throws Exception {
        AcceptanceCriteria savedAcceptanceCriteria = acceptanceCriteriaService.loadAcceptanceCriteriaById(acceptanceCriteriaId);
        return Response.build(acceptanceCriteriaResource.toResource(savedAcceptanceCriteria, boardId, stageId, cardId, userName));
    }

    @RequestMapping(value = "/boards/{boardId}/stages/{stageId}/cards/{cardId}/acceptanceCriterias/{acceptanceCriteriaId}", method = RequestMethod.DELETE)
    public HttpEntity removeAcceptanceCriteria(@PathVariable String boardId, @PathVariable String stageId, @PathVariable String cardId, @PathVariable String acceptanceCriteriaId, @RequestHeader String userName) throws Exception {
        acceptanceCriteriaService.removeAcceptanceCriteria(acceptanceCriteriaId, cardId, userName);
        return Response.build(acceptanceCriteriaResource.toResource(boardId, stageId, cardId, userName));
    }

    @RequestMapping(value = "/boards/{boardId}/stages/{stageId}/cards/{cardId}/acceptanceCriterias", method = RequestMethod.GET)
    public HttpEntity loadAcceptanceCriteriasByCardId(@PathVariable String boardId, @PathVariable String stageId, @PathVariable String cardId, @RequestHeader String userName) throws Exception {
        List<AcceptanceCriteria> acceptanceCriteriaList = acceptanceCriteriaService.loadAcceptanceCriteriasByCardId(cardId);
        return Response.build(acceptanceCriteriasResource.toResource(acceptanceCriteriaList, boardId, stageId, cardId, userName));
    }

    @RequestMapping(value = "/boards/{boardId}/stages/{stageId}/cards/{cardId}/acceptanceCriterias/movement", method = RequestMethod.PUT)
    public HttpEntity resortAcceptCriterias(@RequestBody List<AcceptanceCriteria> acceptanceCriterias, @PathVariable String boardId, @PathVariable String stageId, @PathVariable String cardId, @RequestHeader String userName) throws Exception {
        List<AcceptanceCriteria> acceptanceCriteriaList = acceptanceCriteriaService.resortAcceptCriterias(cardId, acceptanceCriterias, userName);
        return Response.build(acceptanceCriteriasResource.toResource(acceptanceCriteriaList, boardId, stageId, cardId, userName));
    }
}
