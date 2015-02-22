/**
 * Karger's algorithm - a randomized contraction algorithm for finding
 * a minimum cut in an undirected graph.
 * (http://en.wikipedia.org/wiki/Karger%27s_algorithm)
 *
 * This is a "naive" implementation as a new graph is created from
 * the old one in each iteration.
 *
 *  Run-time complexity: O(n2)
 */
function Graph() {
    this.vertices = [],
    this.edges = []
}



