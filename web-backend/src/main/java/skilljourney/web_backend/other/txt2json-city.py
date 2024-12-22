import json

# Chemin vers le fichier texte source
input_file = "cities1000.txt"
output_file = "city_names_sorted.json"

# Conversion : garder uniquement les noms des villes
city_names = []
with open(input_file, "r", encoding="utf-8") as file:
    for line in file:
        parts = line.strip().split("\t")  # Séparer par tabulations
        city_name = parts[1]  # La deuxième colonne correspond au nom de la ville
        city_names.append(city_name)

# Trier les noms des villes par ordre alphabétique
city_names = sorted(city_names)

# Sauvegarder en JSON
with open(output_file, "w", encoding="utf-8") as json_file:
    json.dump(city_names, json_file, ensure_ascii=False, indent=2)

print(f"Extraction et tri terminés ! {len(city_names)} noms de villes sauvegardés dans {output_file}.")
