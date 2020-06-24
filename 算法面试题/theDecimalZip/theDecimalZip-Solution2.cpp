// Date: 28/06/2017
// For CitiGroup tech interview
// The Decimal Zip Problem Solver

// you can use includes, for example:
#include <algorithm>
#include <sstream>
#include <string>
#include <iostream>
using namespace std;
// you can write to stdout for debugging purposes, e.g.
// cout << "this is a debug message" << endl;

int solution(int A, int B);

int main (){
    
    int test1 = solution(12, 56);
    int test2 = solution(1234, 56);
    int test3 = solution(12, 5678);
    cout << test1 << endl << test2 << endl << test3 << endl;
    return 0;
}

int solution(int A, int B) {
    // write your code in C++14 (g++ 6.2.0)    
    stringstream ssa, ssb;
    ssa << A;
    ssb << B;
    string stra(ssa.str());
    string strb(ssb.str());
    string strr("");
    for (size_t i = 0; i < stra.length() or i < strb.length(); ++i){
        if (i < stra.length()) strr += stra[i];
        if (i < strb.length()) strr += strb[i];
    }   
    stringstream ssresult(strr);
    int result;
    ssresult >> result;
    return result;
}