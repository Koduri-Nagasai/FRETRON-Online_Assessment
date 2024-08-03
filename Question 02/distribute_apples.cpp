#include <bits/stdc++.h>
using namespace std;

void distributeApples() {
    const int PaidbyRam = 50;
    const int PaidbySham = 30;
    const int PaidbyRahim = 20;
    const int totalPayment = PaidbyRam + PaidbySham + PaidbyRahim;

    vector<int> appleWeights;
    int weight;
    cout << "Enter apple weight in gram (-1 to stop): " << endl;
    while (true) {
        cin >> weight;
        if (weight == -1) break;
        appleWeights.push_back(weight);
    }

    int totalWeight = 0;
    for (size_t i = 0; i < appleWeights.size(); ++i) {
        totalWeight += appleWeights[i];
    }

    double ramTarget = (PaidbyRam / static_cast<double>(totalPayment)) * totalWeight;
    double shamTarget = (PaidbySham / static_cast<double>(totalPayment)) * totalWeight;
    double rahimTarget = (PaidbyRahim / static_cast<double>(totalPayment)) * totalWeight;

    sort(appleWeights.begin(), appleWeights.end(), greater<int>());

    vector<int> ramApples, shamApples, rahimApples;
    double ramCurrentWeight = 0.0, shamCurrentWeight = 0.0, rahimCurrentWeight = 0.0;

    for (size_t i = 0; i < appleWeights.size(); ++i) {
        int x = appleWeights[i];
        if (ramCurrentWeight + x <= ramTarget) {
            ramApples.push_back(x);
            ramCurrentWeight += x;
        } else if (shamCurrentWeight + x <= shamTarget) {
            shamApples.push_back(x);
            shamCurrentWeight += x;
        } else if (rahimCurrentWeight + x <= rahimTarget) {
            rahimApples.push_back(x);
            rahimCurrentWeight += x;
        }
    }

    cout << "\nDistribution Result:" << endl;
    cout << "Ram: ";
    for (size_t i = 0; i < ramApples.size(); ++i) {
        cout << ramApples[i] << " ";
    }
    cout << endl;

    cout << "Sham: ";
    for (size_t i = 0; i < shamApples.size(); ++i) {
        cout << shamApples[i] << " ";
    }
    cout << endl;

    cout << "Rahim: ";
    for (size_t i = 0; i < rahimApples.size(); ++i) {
        cout << rahimApples[i] << " ";
    }
    cout << endl;
}

int main() {
    distributeApples();
    return 0;
}
