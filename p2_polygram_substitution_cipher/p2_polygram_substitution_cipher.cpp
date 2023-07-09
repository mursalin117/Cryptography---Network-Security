#include <bits/stdc++.h>
using namespace std;

map <string, string> encoder, decoder;

void createDictionary() {
    ifstream input;
    input.open("p2_dictionary.txt");

    string s1, s2;

    while (input >> s1 >> s2) {
        encoder[s1] = s2;
        decoder[s2] = s1;
    }
    input.close();
}

string cipherText(string str) {
    string cipher = "", temp = "";

    for (int i = 0; i < str.size(); i++) {
        if (isalpha(str[i])) {
            temp += str[i];

            if (encoder.find(temp) != encoder.end()) {
                cipher += encoder[temp];
                temp = "";
            }
        }
        else {
            cipher += str[i];
        }
    }

    return cipher;
}

string decipherText(string str) {
    string decipher = "", temp = "";

    for (int i = 0; i < str.size(); i++) {
        if (isalpha(str[i])) {
            temp += str[i];

            if (decoder.find(temp) != decoder.end()) {
                decipher += decoder[temp];
                temp = "";
            }
        }
        else {
            decipher += str[i];
        }
    }

    return decipher;
}

int main() {
    ifstream input;
    input.open("p2_input.txt");
    ofstream output;
    output.open("p2_output.txt");

    createDictionary();

    string str, cipher, decipher; 
    getline(input, str);

    cipher = cipherText(str);
    decipher = decipherText(cipher);

    cout << "Plain-Text: " << str << endl;
    cout << "Cipher-Text: " << cipher << endl;
    cout << "Decipher-Text: " << decipher << endl;

    output << "Plain-Text: " << str << endl;
    output << "Cipher-Text: " << cipher << endl;
    output << "Decipher-Text: " << decipher << endl;

    return 0;
}