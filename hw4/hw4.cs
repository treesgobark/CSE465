using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace hw4
{
    class hw4
    {
        public static void Main(String[] args)
        {
            var data = File.ReadAllLines($@"../../testFiles/{args[0]}");
            string template = File.ReadAllText($@"../../testFiles/{args[1]}");
            //System.Console.WriteLine(data);
            //System.Console.WriteLine(template);
            int count = 0;
            DataTable table = new DataTable();
            foreach (string line in data)
            {
                System.Console.WriteLine("Line: {0}: {1}", count, line);
                var values = line.Split('\t');
                if (count == 0)
                {
                    foreach (string value in values)
                    {
                        table.Columns.Add(value);
                    }
                }
                else
                {
                    table.Rows.Add(values);
                }
                count++;
            }
            foreach (DataRow row in table.Rows)
            {
                string newTemp = String.Copy(template);
                newTemp = newTemp.Replace("<<COURSE>>", (string)row["COURSE"]);
                newTemp = newTemp.Replace("<<ID>>", (string)row["ID"]);
                newTemp = newTemp.Replace("<<TOTAL>>", (string)row["TOTAL"]);
                newTemp = newTemp.Replace("<<SUBTOTAL>>", (string)row["SUBTOTAL"]);
                newTemp = newTemp.Replace("<<LATEDEDUCTION>>", (string)row["LATEDEDUCTION"]);
                newTemp = newTemp.Replace("<<DUE>>", (string)row["DUE"]);
                newTemp = newTemp.Replace("<<SUBMITTED>>", (string)row["SUBMITTED"]);
                newTemp = newTemp.Replace("<<MINUTESLATE>>", (string)row["MINUTESLATE"]);
                newTemp = newTemp.Replace("<<P1>>", (string)row["P1"]);
                newTemp = newTemp.Replace("<<P1COMMENTS>>", (string)row["P1COMMENTS"]);
                newTemp = newTemp.Replace("<<P2>>", (string)row["P2"]);
                newTemp = newTemp.Replace("<<P2COMMENTS>>", (string)row["P2COMMENTS"]);
                File.WriteAllText($@"../../testFiles/{row["id"]}.txt", newTemp);
            }
        }
    }
}
