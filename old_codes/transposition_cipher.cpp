#include  <bits/stdc++.h>

using namespace std;

int main() {
    string text = "DEPARTMENT OF COMPUTER SCIENCE AND ENGINEERING";
    string cipher = "";
    
    int col;
    printf("Enter the no of column size: ");
    cin >> col;

    cout << text << endl;

    int row = text.size() / col;
    if (text.size() % col != 0) 
        row++;

    for (int i = 0; i < col; i++) {
        for (int j = i; j < text.size(); ) {
            cipher += text[j];
            j += col;
        }
    }
    cout << cipher << endl;

    string decipher = "";
    int cnt = cipher.size() % col;
    // cout << row << endl;
    int total = 0;

    for (int i = 0; i < row; i++) {
        int temp = cnt;
        for (int j = i; j < cipher.size(); ) {
            decipher += cipher[j];
            if (temp > 0) {
                j += row;
                temp--;
            } else {
                j += (row-1);
            }
            total++;
            if (total == cipher.size()) {
                break;
            }
            // if (i == row-1) {
            //     break;
            // }
            // cout << decipher << endl;
            // cout << j << endl;
            
        }
    }

    cout << decipher << endl;

    return 0;
}
