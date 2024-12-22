import json

# Chemin vers le fichier texte source
input_file = "cities1000.txt"
output_file = "cities.json"

# Colonnes à extraire
columns = [
    "geonameid", "name", "asciiname", "alternatenames",
    "latitude", "longitude", "feature_class", "feature_code",
    "country_code", "admin1_code", "admin2_code", "admin3_code",
    "admin4_code", "population", "elevation", "dem",
    "timezone", "modification_date"
]

# Conversion du fichier texte en JSON
cities = []
with open(input_file, "r", encoding="utf-8") as file:
    for line in file:
        parts = line.strip().split("\t")
        city = {
            "geonameid": parts[0],
            "name": parts[1],
            "asciiname": parts[2],
            "alternatenames": parts[3].split(","),
            "latitude": float(parts[4]),
            "longitude": float(parts[5]),
            "feature_class": parts[6],
            "feature_code": parts[7],
            "country_code": parts[8],
            "admin1_code": parts[10],
            "admin2_code": parts[11],
            "admin3_code": parts[12],
            "admin4_code": parts[13],
            "population": int(parts[14]) if parts[14].isdigit() else None,
            "elevation": int(parts[15]) if parts[15].isdigit() else None,
            "dem": int(parts[16]) if parts[16].isdigit() else None,
            "timezone": parts[17],
            "modification_date": parts[18],
        }
        cities.append(city)

# Sauvegarder en fichier JSON
with open(output_file, "w", encoding="utf-8") as json_file:
    json.dump(cities, json_file, ensure_ascii=False, indent=2)

print(f"Conversion terminée ! {len(cities)} villes sauvegardées dans {output_file}.")
