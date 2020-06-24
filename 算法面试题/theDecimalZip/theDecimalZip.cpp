// Date: 28/06/2017
// For CitiGroup tech interview
// The Decimal Zip Problem Solver

// you can use includes, for example:
#include <algorithm>
#include <sstream>
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
    stringstream ssa, ssb, ssresult;
    ssa << A;
    ssb << B;   
    char tempa, tempb;
    int counter = 1;
    while (ssa >> tempa and ssb >> tempb) {   
        ssresult << tempa << tempb;    
        counter++;            
    }   
    if (counter > ssa.str().length() and counter < ssb.str().length())
            ssresult << ssb.str().substr(counter-1);            
               
    if (counter > ssb.str().length() and counter < ssa.str().length())
            ssresult << ssa.str().substr(counter-1);                  
    int result;
    ssresult >> result;
    return result;
}