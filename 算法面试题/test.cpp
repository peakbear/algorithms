#include <iostream>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

int main () {
	char words[] = "you are welcome";
	string str(words);

	cout << words << endl;
	cout << str << endl;

	for (size_t i = 0; i < str.size() / 2; ++i) {
		char temp = str[i];
		str[i] = str[str.size() - 1 - i];
		str[str.size() - 1 - i] = temp;
	}

	cout << str << endl;

	vector<pair<int, int>> emptySpaces;
	size_t startPos = 0;
	size_t endPos = str.size() - 1;
	for (size_t i = 1; i < endPos - 1; ++i) {
		if (str[i] == ' ') {
			emptySpaces.push_back(make_pair(startPos, i - 1));
			startPos = i + 1;
		}
	}
	emptySpaces.push_back(make_pair(startPos, endPos));


	for (size_t k = 0; k < emptySpaces.size(); ++k) {
		size_t startPos = emptySpaces[k].first;
		size_t endPos = emptySpaces[k].second;
		for (size_t i = startPos; i < (startPos + endPos) / 2; ++i) {
			char temp = str[i];
			str[i] = str[endPos - (i - startPos)];
			str[endPos - (i - startPos)] = temp;
		}
	}


	cout << str << endl;




	return 0;
}