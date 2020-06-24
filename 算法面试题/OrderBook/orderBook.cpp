#include <iostream>
#include <map>
#include <list>
#include <vector>
#include <fstream>
#include <sstream>
#include <string>
#include <cmath>

using namespace std;

class OrderBook {
private:
    list<pair<int32_t, double>> sortedOrders; // pair<id, price>
    vector<pair<int, double>> highestPriceLog; // pair<timestamp, highestPrice>
    void populateDataFromFile(string fileName);
public:
	OrderBook(string fileName) { populateDataFromFile(fileName); }
    void insert(int32_t id, double price);
    void erase(int32_t id);
    double getHighestPrice() const;
    vector<pair<int, double>> getHighestPriceStamp() const { return highestPriceLog; }
};

void OrderBook::populateDataFromFile(string fileName) {
    ifstream file(fileName);
    string line;
    while (getline(file, line)) {
        istringstream iss(line);
        int timestamp;
        char operation;
        int32_t id;
        iss >> timestamp >> operation >> id;
        if (operation == 'I') {
            double price;
            iss >> price;
            this->insert(id, price);
        } else {
            this->erase(id);
        }
        highestPriceLog.emplace_back(timestamp, this->getHighestPrice());
    }
}

void OrderBook::insert(int32_t id, double price) {
    for (auto i = sortedOrders.begin(); i != sortedOrders.end(); ++i) {
        if (price >= i->second) {
            sortedOrders.insert(i, make_pair(id, price));
            return;
        }
    }
    sortedOrders.push_back(make_pair(id, price));
}

void OrderBook::erase(int32_t id) {
    for (auto i = sortedOrders.begin(); i != sortedOrders.end(); ++i) {
        if (i->first == id) {
            sortedOrders.erase(i);
            return;
        }
    }
}

double OrderBook::getHighestPrice() const {
    return sortedOrders.empty() ? NAN : sortedOrders.front().second;
}

int main(int argc, char *argv[]) {
    OrderBook orderBook(argv[1]);
    double priceSum = 0;
    int timeSum = 0;
    vector<pair<int, double>> highestPriceStamp = orderBook.getHighestPriceStamp();
    for (auto i = highestPriceStamp.begin(); i != highestPriceStamp.end() - 1; ++i) {
        if (!isnan(i->second)) {
            int period = (i + 1)->first - i->first;
            priceSum += period * i->second;
            timeSum += period;
        }
    }
    cout << "Time Weighted Average Highest Price: " << priceSum / timeSum << endl;
    return 0;
}