<%--
  Created by IntelliJ IDEA.
  User: youss
  Date: 13/02/2025
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GestionDeStock</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
</head>
<body class="bg-gradient-to-br from-red-700 to-black text-white">

<!-- Navbar -->
<nav class="bg-black bg-opacity-50 py-4">
    <div class="max-w-7xl mx-auto px-6">
        <h1 class="text-2xl font-bold text-white tracking-wide">GestionProduits</h1>
    </div>
</nav>

<!-- Main Container -->
<div class="max-w-6xl mx-auto py-10 px-4">

    <!-- Add Product Modal -->
    <div class="bg-white text-gray-900 p-8 rounded-lg shadow-xl w-full max-w-lg mx-auto">
        <h2 class="text-xl font-semibold mb-6">Ajouter un Produit</h2>
        <form id="productForm">
            <div class="mb-4">
                <label class="block text-gray-700 mb-2">Nom du produit</label>
                <input type="text" class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:ring-2 focus:ring-red-500" required>
            </div>
            <div class="mb-4">
                <label class="block text-gray-700 mb-2">Description</label>
                <textarea class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:ring-2 focus:ring-red-500" required></textarea>
            </div>
            <div class="grid grid-cols-2 gap-4 mb-4">
                <div>
                    <label class="block text-gray-700 mb-2">Quantité en stock</label>
                    <input type="number" class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:ring-2 focus:ring-red-500" min="1" required>
                </div>
                <div>
                    <label class="block text-gray-700 mb-2">Prix unitaire ($)</label>
                    <input type="number" class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:ring-2 focus:ring-red-500" min="0.01" step="0.01" required>
                </div>
            </div>
            <div class="mb-6">
                <label class="block text-gray-700 mb-2">Catégorie du produit</label>
                <select class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:ring-2 focus:ring-red-500">
                    <option>Électronique</option>
                    <option>Vêtements</option>
                    <option>Alimentation</option>
                    <option>Maison & Jardin</option>
                    <option>Autre</option>
                </select>
            </div>
            <div class="flex justify-end space-x-3">
                <button type="submit" class="bg-red-600 hover:bg-red-700 text-white px-5 py-2 rounded-lg transition">Ajouter</button>
            </div>
        </form>
    </div>

    <!-- Product Table -->
    <div class="bg-white text-gray-900 shadow-lg rounded-lg p-6 mt-10">
        <h2 class="text-2xl font-semibold mb-4">Liste des Produits</h2>
        <table class="w-full border-collapse border border-gray-300 text-gray-800">
            <thead class="bg-gray-200">
            <tr>
                <th class="border border-gray-300 px-4 py-3">ID</th>
                <th class="border border-gray-300 px-4 py-3">Nom du Produit</th>
                <th class="border border-gray-300 px-4 py-3">Prix ($)</th>
                <th class="border border-gray-300 px-4 py-3">Stock</th>
                <th class="border border-gray-300 px-4 py-3">Catégorie</th>
                <th class="border border-gray-300 px-4 py-3">Actions</th>
            </tr>
            </thead>
            <tbody id="productTable">
            <tr class="hover:bg-gray-100 transition">
                <td class="border border-gray-300 px-4 py-3 text-center">1</td>
                <td class="border border-gray-300 px-4 py-3">Produit Exemple</td>
                <td class="border border-gray-300 px-4 py-3">20.00 $</td>
                <td class="border border-gray-300 px-4 py-3">10</td>
                <td class="border border-gray-300 px-4 py-3">Électronique</td>
                <td class="border border-gray-300 px-4 py-3 flex justify-center space-x-2">
                    <button class="text-yellow-500 hover:text-yellow-700"><i class="bi bi-pencil-square text-lg"></i></button>
                    <button class="text-red-500 hover:text-red-700"><i class="bi bi-trash text-lg"></i></button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

</div>


<script>
    document.getElementById("productForm").addEventListener("submit", (event) => {
        event.preventDefault();
        alert("Produit ajouté avec succès !");
    });
</script>

</body>
</html>
