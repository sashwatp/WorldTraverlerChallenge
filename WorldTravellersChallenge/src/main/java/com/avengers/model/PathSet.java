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
            if (path.isValid()) {
                if (path.HasRestricted()) {
                    if (shortestRestrictedPath == null) {
                        shortestRestrictedPath = path;

                    } else if (shortestRestrictedPath.getPathLength() > path.getPathLength()) {
                        shortestRestrictedPath = path;

                    }

                } else if (shortestUnrestrictedPath == null) {
                    shortestUnrestrictedPath = path;
                } else if (shortestUnrestrictedPath.getPathLength() > path.getPathLength()) {
                    shortestUnrestrictedPath = path;
                }
            }
        }

    }

}
