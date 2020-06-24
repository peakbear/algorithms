#include<stdio.h>
#include<stdlib.h>

struct Node {
	int data;
	struct Node* next;
};


void printList (struct Node* head);
int findMidEle(struct Node* head);
int find3rdEle(struct Node* head);
bool hasLoop(struct Node* head);


int main(int argc, char const *argv[])
{
	struct Node* head = (struct Node*)malloc(sizeof(struct Node));
	struct Node* sec = (struct Node*)malloc(sizeof(struct Node));
	struct Node* trd = (struct Node*)malloc(sizeof(struct Node));
	struct Node* fth = (struct Node*)malloc(sizeof(struct Node));

	head->data = 1;
	sec->data = 2;
	trd->data = 3;
	fth->data = 4;

	head->next = sec;
	sec->next = trd;
	trd->next = fth;
	fth->next = NULL;

	printList(head);

	// REVERSE LINKEDLIST
	// struct Node* lastPos = NULL;
	// struct Node* nextPos = NULL;

	// while (head->next != NULL) {
	// 	nextPos = head->next;
	// 	head->next = lastPos;
	// 	lastPos = head;
	// 	head = nextPos;
	// }
	// head -> next = lastPos;

	// printList(head);

	// FIND MIDDLE ELEMENTS
	// int midEle = findMidEle(head);
	// printf("%d\n", midEle);

	// FIND 3rd ELEMENTS
	int ele = find3rdEle(head);
	printf("%d\n", ele);


	// IDENTIFY LOOP
	bool looped = hasLoop(head);
	printf("%d\n", looped);

	return 0;
}

void printList (struct Node* head) {
	if (head != NULL) {
		printf("%d\n", head->data);
		printList(head->next);
	}
	printf("\n");
}

int findMidEle(struct Node* head) {
	struct Node* slowCur = head;
	struct Node* fastCur = head;

	if (head != NULL) {
		while (fastCur != NULL and fastCur->next != NULL) {
			slowCur = slowCur->next;
			fastCur = fastCur->next->next;
		}
		return slowCur->data;
	}
	return -1;
}

int find3rdEle(struct Node* head) {
	struct Node* slowCur = head;
	struct Node* fastCur = head;

	if (head != NULL) {
		try {
			fastCur = head->next->next->next;
			while (fastCur != NULL) {
				slowCur = slowCur->next;
				fastCur = fastCur->next;
			}
			return slowCur->data;
		} catch (...) {
			printf("catched\n");
		}
	}
	return -1;
}


bool hasLoop(struct Node* head) {
	struct Node* slowCur = head;
	struct Node* fastCur = head;

	if (head != NULL) {
		while (fastCur != NULL and fastCur->next != NULL) {
			slowCur = slowCur->next;
			fastCur = fastCur->next->next;
			if (slowCur == fastCur) {
				return true;
			}
		}
	}
	return false;
}


