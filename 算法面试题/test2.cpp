#include <iostream>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

int main () {
	char words[] = "  you are welcome  ";
	string str(words);

	cout << words << endl;
	cout << str << endl;

	vector<pair<int, int>> substrs;

	int startPos = str.find_first_not_of(' ');
	int endPos = str.find_last_not_of(' ');

	int forwardspace = str.find(' ', startPos);
	int backwardspace = str.rfind(' ', endPos);

	while (forwardspace <= backwardspace) {

		string temp = str.substr(startPos, forwardspace - startPos);
		string temp2 = str.substr(backwardspace +1, endPos - backwardspace);

		cout << temp << endl;
		cout << temp2 << endl;

		str.replace(startPos, forwardspace - startPos, temp2);
		cout << str << endl;
		str.replace(backwardspace +1, endPos - backwardspace, temp);
		cout << str << endl;

		startPos = str.find_first_not_of(' ', forwardspace);
		endPos = str.find_last_not_of(' ', backwardspace);
		forwardspace = str.find(' ', startPos);
		backwardspace = str.rfind(' ', endPos);
	}

	cout << str << endl;

	

	return 0;
}