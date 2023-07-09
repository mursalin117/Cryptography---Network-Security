#include <bits/stdc++.h>
using namespace std;

string transposition(string str, int n) {
    string transposed = "";
    int col = n;
    int row = str.size() / col;
    if (str.size() % col != 0) {
        row++;
    }

    for (int i = 0; i < col; i++) {
        for (int j = i; j < str.size(); ) {
            transposed += str[j];
            j += col;
        }
    }

    return transposed;
}

string reverseTransposition(string str, int n) {
    string reverseTransposed = "";
    int col = n;
    int row = str.size() / col;
    if (str.size() % col != 0) {
        row++;
    }
    int cnt = str.size() % col;
    int total = 0;

    for (int i = 0; i < row; i++) {
        int temp = cnt;
        if (temp != 0) {
            for (int j = i; j < str.size(); ) {
                reverseTransposed += str[j];
                if (temp > 0) {
                    j += row;
                    temp--;
                }
                else {
                    j += (row-1);
                }
                total++;
                if (total == str.size()) {
                    break;
                }
            }
        } 
        else {
            for (int j = i; j < str.size(); ) {
                reverseTransposed += str[j];
                j += row;
            }
        }
    }

    return reverseTransposed;
}

string cipherText(string str, int n) {
    string cipher = transposition(transposition(str, n), n);
    return cipher;
}

string decipherText(string str, int n) {
    string decipher = reverseTransposition(reverseTransposition(str, n), n);
    return decipher;
}

int main() {
    // string text = "DEPARTMENT OF COMPUTER SCIENCE";
    // string text = "abcdefghijkl";
    string text = "DEPARTMENT OF COMPUTER SCIENCE AND ENGINEERING";
    string cipher, decipher;

    int n; 
    cout << "Enter the size of the column: ";
    cin >> n;

    cipher = cipherText(text, n);
    decipher = decipherText(cipher, n);

    cout << "Plain-Text: " << text << endl;
    cout << "Cipher-Text: " << cipher << endl;
    cout << "Decipher-Text: " << decipher << endl;

    return 0;
}