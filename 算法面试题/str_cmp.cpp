int str_cmp(char* s1, char* s2) {
	if (s1 == s2) return 0;

	if (sizeof(*s1) == 0 && sizeof(*s2) != 0) return -1;

	if (sizeof(*s1) != 0 && sizeof(*s2) == 0) return 1;

	for (size_t i = 0; i < sizeof(*s1); ++i) {
		if (i >= sizeof(*s2)) return 1;
		if (s1[i] > s2[i]) return 1;
		if (s1[i] < s2[i]) return -1;
	}

    if (sizeof(*s1) == sizeof(*s2)) return 0;

	return -1;
}