// 15-May-2017
// Codibility Test 
// =================================
// City Network Problem
// there are N cities with N-1 roads that links each city
// there is only one captipal
// there is only one unique route between 2 cities
// cities are numbered as T[0], T[1], T[2], T[3]....
// while the value of them represents the upstream city towards the captical
// the capitals's value equals to index
// e.g.
// T[0] = 2, T[1] = 4, T[2] = 2, T[3] = 0, T[4] = 7, T[5] = 6, T[6] = 0, T[7] = 2, T[8] =  6
// T[2] is the Captical as T[2] = 2
// T[0]'s value is 2 means T[0] -> T[2], so the distance from T[0] to Captical is 1. 
// This relationship can be represented by:
// 	   2
//     /\
//    0  7
//    |\  \
//    6 3  4
//    |  \  \
//    5   8  1
// The question asks a solution to return a vector<N-1>, indicating the number of cities
// at the distance of 1, 2 .... N-1
// in this case [2, 3, 3, 0, 0, 0, 0, 0] should be returned



#include <iostream>
#include <vector>
using namespace std;

void findCap (const vector<int> &T, const vector<int> &dis, vector<int> &temp, int curCity);
vector<int> solution (vector<int> T);


int main (){		
	vector<int> T = {2, 4, 2, 0, 7, 6, 0, 2, 6};
	// vector<int>	T = {};
	// vector<int>	T = {0};

	vector<int> result = solution(T);
	for (auto re:result)
		cout << re << ", ";
	cout << endl;
	return 0;
}

void findCap (const vector<int> &T, const vector<int> &dis, vector<int> &temp, int curCity){
	temp.push_back(curCity);
	int upCity = T[curCity];
	if (dis[upCity] == -1){		
		return findCap(T, dis, temp, upCity);
	}
	temp.push_back(upCity);
	return;
}

vector<int> solution (vector<int> T){	
	int N = T.size();
	if (N <= 1) return {0};
	int capIndex = 0;		
	vector<int> dis(N, -1);		
	for (auto i = 0; i < N; i++){
		if (T[i] == i){
			capIndex = i;
			dis[i] = 0;
		}
	}		
	// for (auto i = 0; i < N; i++) cout << dis[i]; cout << endl;
	for (auto i = 0; i < N; i++){	
		if (dis[i] == -1){			
			vector<int> temp;
			findCap(T, dis, temp, i);		
			int rootDis = dis[temp.back()];
			for (auto j = 0; j < temp.size()-1; j++){
				int cityIndex = temp[j];
				dis[cityIndex] = rootDis + temp.size() - 1 - j;
				// cout << "dis" << cityIndex << " = " << rootDis << " + " << temp.size() - 1 - j << " = " << dis[cityIndex] << endl;			
			}
		}
	}

	vector<int> result (N-1, 0);
	for (auto distance:dis){
		if (distance != 0)
			result[distance-1]++;		
	}
	return result;
}