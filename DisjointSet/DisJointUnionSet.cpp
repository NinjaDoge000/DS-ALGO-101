#include<bits/stdc++.h>
using namespace std;

class DSU {

    vector<int> parent;
    vector<int> rank;
    int n;

    public:

    DSU(int size) {
        n = size;
        makeSet(n);
    }

    void makeSet(int n);
    int findParent(int a);
    void unionSet(int a, int b);
    bool isConnected(int a, int b);
};

void DSU::makeSet(int n) {
    parent = vector<int>(n, 0);
    iota(parent.begin(), parent.end(), 0);
    rank = vector<int>(n, 0);
}

int DSU::findParent(int a) {

    if (parent[a] == a) return a;
    return parent[a] = findParent(parent[a]);
}

void DSU::unionSet(int a, int b) {
    int u = findParent(a);
    int v = findParent(b);

    if (rank[u] > rank[v]) parent[v] = u;
    else if (rank[v] > rank[u]) parent[u] = v;
    else {
        rank[u]++;
        parent[v] = u;
    }
}

bool DSU::isConnected(int a, int b) {
		return findParent(a) == findParent(b);
}

int main() {
    DSU dsu(5);
    dsu.unionSet(2, 4);
    dsu.unionSet(3, 2);
    dsu.unionSet(0, 1);
    cout << dsu.findParent(1);
}