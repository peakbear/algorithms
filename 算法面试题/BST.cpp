// A simple C program for traversal of a linked list
#include<stdio.h>
#include<stdlib.h>
#include<limits.h>

struct Node
{
	int data;
	struct Node *leftNode;
	struct Node *rightNode;
};

// This function prints contents of linked list starting from
// the given node
void printList(struct Node *n)
{
	if (n != NULL)
	{
		printf(" %d\n", n->data);
		printList(n->leftNode);
		printList(n->rightNode);
	}
}

int sumTree (struct Node* n) {
	if (n != NULL) {
		return n->data + sumTree(n->leftNode) + sumTree(n->rightNode);
	}
	return 0;
}

bool checkBST(struct Node* n, int min, int max) {
	bool left = true, right = true;
	if (n != NULL) {
		if (n->leftNode != NULL) {
			if (n->leftNode->data >= n->data || n->leftNode->data <= min) {
				left = false;
			}
		}
		if (n->rightNode != NULL) {
			if (n->rightNode->data <= n->data || n->rightNode->data >= max) {
				right = false;
			}
		}
		return left && right && checkBST(n->leftNode, min, n->data) && checkBST(n->rightNode, n->data, max);
	}
	return true;
}

void insertNode (struct Node* newNode, struct Node* n) {
	if (n != NULL) {
		if (newNode->data > n->data) {
			if (n->rightNode == NULL) {
				n->rightNode = newNode;
			} else {
				insertNode(newNode, n->rightNode);
			}
			return;
		} else if (newNode->data < n->data) {
			if (n->leftNode == NULL) {
				n->leftNode = newNode;
			} else {
				insertNode(newNode, n->leftNode);
			}
			return;
		} else
			return;
	}
	return;
}

int main()
{
	struct Node* head = NULL;
	struct Node* second = NULL;
	struct Node* third = NULL;
	struct Node* fourth = NULL;

// allocate 3 nodes in the heap
	head = (struct Node*)malloc(sizeof(struct Node));
	second = (struct Node*)malloc(sizeof(struct Node));
	third = (struct Node*)malloc(sizeof(struct Node));
	fourth = (struct Node*)malloc(sizeof(struct Node));

	head->data = 5; //assign data in first node
	head->leftNode = second; // Link first node with second
	head->rightNode = third; // Link first node with second

	second->data = 1; //assign data to second node
	second->rightNode = fourth;

	third->data = 7; //assign data to third node

	fourth->data = 4; //assign data to third node


	printList(head);

	int sum = sumTree(head);
	printf("sum = %d\n", sum);

	int min = INT_MIN;
	int max = INT_MAX;

	bool isBST = checkBST(head, min, max);
	printf("isBST: %d\n", isBST);

	struct Node* fivth = (struct Node*)malloc(sizeof(struct Node));
	fivth->data = 2;
	insertNode(fivth, head);

	struct Node* sixth = (struct Node*)malloc(sizeof(struct Node));
	sixth->data = 0;
	insertNode(sixth, head);

	printList(head);

	sum = sumTree(head);
	printf("sum = %d\n", sum);
	isBST = checkBST(head, min, max);
	printf("isBST: %d\n", isBST);



	return 0;
}
