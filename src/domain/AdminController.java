package domain;


import domain.graph.*;
import exceptions.*;
import util.ProjectConstants;

public class AdminController {

    private int lastIdAuthor = -1;
    private int lastIdConference = -1;
    private int lastIdPaper = -1;
    private int lastIdTerm = -1;

    private void lastId() {
        for (Node node : Graph.getInstance().allNodes()) {
            int id = node.getId().getId();
            if (node.asAuthor() != null) {
                if (lastIdAuthor < id) lastIdAuthor = id;
            }
            else if (node.asPaper() != null) {
                if (lastIdPaper < id) lastIdPaper = id;
            }
            else if (node.asConference() != null) {
                if (lastIdConference < id) lastIdConference = id;
            }
            else if (node.asTerm() != null) {
                if (lastIdTerm < id) lastIdTerm = id;
            }
            else throw new ProjectError("FATAL ERROR: Node "+ node +" it's not a valid type");
        }

        ++lastIdAuthor;
        ++lastIdConference;
        ++lastIdPaper;
        ++lastIdTerm;
    }

    public void resetGraph() {
        Graph.getInstance().resetGraph();
    }

    public void restoreGrapgh() {
        resetGraph();
        DataBaseController.loadOriginal();
        PageRank.execute();
    }

    public void addNewNode(String name, int label, int type) throws ExistingNode {
        Node nodeToInsert;
        if (lastIdAuthor < 0) lastId();
        switch (type) {
            case ProjectConstants.AUTHOR_TYPE:
                if (label == -1) nodeToInsert = new Author(name, lastIdAuthor);
                else nodeToInsert = new Author(name, lastIdAuthor, label);
                ++lastIdAuthor;
                break;
            case ProjectConstants.CONFERENCE_TYPE:
                if (label == -1) nodeToInsert = new Conference(name, lastIdConference);
                else nodeToInsert = new Conference(name, lastIdConference, label);
                ++lastIdConference;
                break;
            case ProjectConstants.PAPER_TYPE:
                if (label == -1) nodeToInsert = new Paper(name, lastIdPaper);
                else nodeToInsert = new Paper(name, lastIdAuthor, label);
                ++lastIdPaper;
                break;
            case ProjectConstants.TERM_TYPE:
                nodeToInsert = new Term(name, lastIdTerm);
                ++lastIdTerm;
                break;
            default:
                throw new ProjectError("Invalid parameter type: "+type);
        }
        Graph.getInstance().addNode(nodeToInsert);
    }

    public void deleteNode(int id, int type) throws NonExistentNode {
        Id nodeId;
        switch (type) {
            case ProjectConstants.AUTHOR_TYPE:
                nodeId = Author.makeId(id);
                break;
            case ProjectConstants.CONFERENCE_TYPE:
                nodeId = Conference.makeId(id);
                break;
            case ProjectConstants.PAPER_TYPE:
                nodeId = Paper.makeId(id);
                break;
            case ProjectConstants.TERM_TYPE:
                nodeId = Term.makeId(id);
                break;
            default:
                throw new ProjectError("Invalid parameter type: "+ type);
        }
        Graph.getInstance().deleteNode(nodeId);
        DomainController.recalculate();
    }

    public void addNewEdge(int i1, int type1, int i2, int type2) throws ExistingEdge, NonExistentEdgeNodes {
        Id id1, id2;
        switch (type1) {
            case ProjectConstants.AUTHOR_TYPE:
                id1 = Author.makeId(i1);
                break;
            case ProjectConstants.CONFERENCE_TYPE:
                id1 = Conference.makeId(i1);
                break;
            case ProjectConstants.PAPER_TYPE:
                id1 = Paper.makeId(i1);
                break;
            case ProjectConstants.TERM_TYPE:
                id1 = Term.makeId(i1);
                break;
            default:
                throw new ProjectError("Invalid parameter type1: "+type1);
        }
        switch (type2) {
            case ProjectConstants.AUTHOR_TYPE:
                id2 = Author.makeId(i2);
                break;
            case ProjectConstants.CONFERENCE_TYPE:
                id2 = Conference.makeId(i2);
                break;
            case ProjectConstants.PAPER_TYPE:
                id2 = Paper.makeId(i2);
                break;
            case ProjectConstants.TERM_TYPE:
                id2 = Term.makeId(i2);
                break;
            default:
                throw new ProjectError("Invalid parameter type2: "+ type2);
        }
        Graph.getInstance().addEdge(id1, id2);
        DomainController.recalculate();
    }

    public void deleteEdge (int i1, int type1, int i2, int type2) throws NonExistentEdgeNodes, NonExistentEdge {
        Id id1, id2;
        switch (type1) {
            case ProjectConstants.AUTHOR_TYPE:
                id1 = Author.makeId(i1);
                break;
            case ProjectConstants.CONFERENCE_TYPE:
                id1 = Conference.makeId(i1);
                break;
            case ProjectConstants.PAPER_TYPE:
                id1 = Paper.makeId(i1);
                break;
            case ProjectConstants.TERM_TYPE:
                id1 = Term.makeId(i1);
                break;
            default:
                throw new ProjectError("Invalid parameter type1: "+type1);
        }
        switch (type2) {
            case ProjectConstants.AUTHOR_TYPE:
                id2 = Author.makeId(i2);
                break;
            case ProjectConstants.CONFERENCE_TYPE:
                id2 = Conference.makeId(i2);
                break;
            case ProjectConstants.PAPER_TYPE:
                id2 = Paper.makeId(i2);
                break;
            case ProjectConstants.TERM_TYPE:
                id2 = Term.makeId(i2);
                break;
            default:
                throw new ProjectError("Invalid parameter type2: "+ type2);
        }
        Graph.getInstance().deleteEdge(id1, id2);
        DomainController.recalculate();
    }
}
