package com.avengers.model;

import java.util.HashSet;
import java.util.Set;

public class PathSet {

    private Set<Path> Paths;
    private Path shortestRestrictedPath;
    private Path shortestUnrestrictedPath;

    public PathSet() {
        Paths = new HashSet<Path>();
        shortestRestrictedPath = null;
        shortestUnrestrictedPath = null;
    }

    public void addPath(Path path) {
        if (Paths.add(path)) {
            reEvaluate(path);
        }

    }

    public Path getShortestRestrictedPath() {
        return shortestRestrictedPath;
    }

    public Path getShortestUnrestrictedPath() {
        return shortestUnrestrictedPath;
    }

    public Path getShortestPath() {
        Path path = null;
        if (shortestRestrictedPath == null) {
            path = shortestUnrestrictedPath;
        } else if (shortestUnrestrictedPath == null) {
            path = shortestRestrictedPath;
        } else {
            path = (shortestRestrictedPath.getPathLength() > shortestUnrestrictedPath.getPathLength() ? shortestUnrestrictedPath
                    : shortestRestrictedPath);
        }

        return path;
    }

    public void reEvaluate(Path path) {
        if (path.isValid()) {
            if (path.HasRestricted()) {
                setRestrictedPath(path);
            } else {
                setUnrestricedPath(path);
            }
        }
    }

    private void setUnrestricedPath(Path path) {
        if (shortestUnrestrictedPath == null) {
            shortestUnrestrictedPath = path;
        } else if (shortestUnrestrictedPath.getPathLength() > path.getPathLength()) {
            shortestUnrestrictedPath = path;
        }

    }

    private void setRestrictedPath(Path path) {
        if (shortestRestrictedPath == null) {
            shortestRestrictedPath = path;

        } else if (shortestRestrictedPath.getPathLength() > path.getPathLength()) {
            shortestRestrictedPath = path;
        }
    }

}
