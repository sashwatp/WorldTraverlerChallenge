package com.avengers.model;

import java.util.HashSet;
import java.util.Set;

public class PathSet {

    private Set<Path> Paths;
    private Path shortestRestrictedPath;
    private Path shortestUnrestrictedPath;
    private Path longestRestrictedPath;
    private Path longestUnrestrictedPath;

    public PathSet() {
        Paths = new HashSet<Path>();
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
    
    public Path getLongestUnrestrictedPath() {
        return longestUnrestrictedPath;
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
                setShortestRestrictedPath(path);
                setLongestRestrictedPath(path);
            } else {
                setShortestUnrestricedPath(path);
                setLongestRestrictedPath(path);
            }
        }
    }

    private void setShortestUnrestricedPath(Path path) {
        if (shortestUnrestrictedPath == null) {
            shortestUnrestrictedPath = path;
        } else if (shortestUnrestrictedPath.getPathLength() > path.getPathLength()) {
            shortestUnrestrictedPath = path;
        }

    }

    private void setShortestRestrictedPath(Path path) {
        if (shortestRestrictedPath == null) {
            shortestRestrictedPath = path;

        } else if (shortestRestrictedPath.getPathLength() > path.getPathLength()) {
            shortestRestrictedPath = path;
        }
    }

    private void setLongestRestrictedPath(Path path) {
        if (longestRestrictedPath == null) {
            longestRestrictedPath = path;

        } else if (longestRestrictedPath.getPathLength() < path.getPathLength()) {
            longestRestrictedPath = path;
        }
    }

    private void setLongestUnrestricedPath(Path path) {
        if (longestUnrestrictedPath == null) {
            longestUnrestrictedPath = path;
        } else if (longestUnrestrictedPath.getPathLength() < path.getPathLength()) {
            longestUnrestrictedPath = path;
        }

    }

}
